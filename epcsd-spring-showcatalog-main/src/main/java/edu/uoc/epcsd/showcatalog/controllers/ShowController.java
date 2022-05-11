package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getAllShows() {
        log.trace("getAllShows");
        return showRepository.findAll();
    }

    @GetMapping("/{showName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByName() {
        log.trace("getAllShows");
        return showRepository.findAll();
    }

    @GetMapping("/byCategory/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategoryName(@PathVariable("name") String name) {
        log.trace("getAllShows");
        return showRepository.findByCategoriesName(name);
    }

    @GetMapping("/byCategory/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategoryId(@PathVariable("id") Long id) {
        log.trace("getAllShows");
        return showRepository.findByCategoriesId(id);
    }

    @PostMapping("/")
    public Show createShow(@Valid @RequestBody Show show){
        log.trace("createShow");
        return showRepository.save(show);
    }

    @DeleteMapping("/{showId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Boolean> deleteShow(@PathVariable("showId") Long showId) throws ResourceNotFoundException {
        log.trace("deleteShow");
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + showId));
        showRepository.delete(show);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



    // add the code for the missing system operations here
}
