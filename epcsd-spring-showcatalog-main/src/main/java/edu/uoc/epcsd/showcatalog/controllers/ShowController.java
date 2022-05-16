package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.entities.dto.ShowDTO;
import edu.uoc.epcsd.showcatalog.kafka.KafkaConstants;
import edu.uoc.epcsd.showcatalog.repositories.CategoryRepository;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Log4j2
@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getAllShows() {
        log.info("getAllShows");
        return showRepository.findAll();
    }

    @GetMapping("/{showName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByName(@PathVariable("showName") String name) {
        log.info("getAllShowsByName");
        return showRepository.findByName(name);
    }

    @GetMapping("/byCategory/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategoryName(@PathVariable("name") String name) {
        log.info("getAllShowsByCategoryName");
        return showRepository.findByCategoriesName(name);
    }

    @GetMapping("/byCategory/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategoryId(@PathVariable("id") Long id) {
        log.info("getAllShows");
        return showRepository.findByCategoriesId(id);
    }

    @PostMapping("/")
    public Show createShow(@Valid @RequestBody ShowDTO showDTO){
        log.info("createShow");

        /*
        Validate categories input
         */
        List<Category> categories = new ArrayList<>();
        for(Long id : showDTO.getCategories()){
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()) {
                categories.add(category.get());
            }else{
                    log.info("Category not found.");
                }
            }

        Show savedShow = showRepository.save(Show.builder()
                .name(showDTO.getName())
                .image(showDTO.getImage())
                .price(showDTO.getPrice())
                .duration(showDTO.getDuration())
                .capacity(showDTO.getCapacity())
                .onSaleDate(showDTO.getOnSaleDate())
                .categories(categories)
                .build());
        sendMessage(savedShow);

        return savedShow;
    }

    @DeleteMapping("/{showId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Boolean> deleteShow(@PathVariable("showId") Long showId) throws ResourceNotFoundException {
        log.info("deleteShow");
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found for this id: " + showId));
        showRepository.delete(show);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public void sendMessage(Show show){
        log.info(String.format("### -> Producing message -> "+show.getName()));
        kafkaTemplate.send(KafkaConstants.SHOW_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, show);
    }



    // add the code for the missing system operations here
}
