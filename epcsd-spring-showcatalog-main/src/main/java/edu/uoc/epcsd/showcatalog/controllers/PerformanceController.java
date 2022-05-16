package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.entities.dto.PerformanceDTO;
import edu.uoc.epcsd.showcatalog.repositories.PerformanceRepository;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/")
    public List<Performance> getPerformances(){
        return performanceRepository.findAll();
    }

    @GetMapping("/{performanceId}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable("performanceId") Long performanceId)
            throws ResourceNotFoundException {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Performance not found for this id: " + performanceId));
        return ResponseEntity.ok().body(performance);
    }

    @GetMapping("/byShow/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Performance> getPerformanceByShow_Name(@PathVariable("id") Long id) {
        log.info("getAllPerformanceByShowId");
        return performanceRepository.findPerformanceByShow_Id(id);
    }

    @GetMapping("/byShow/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Performance> getPerformanceByShow_Name(@PathVariable("name") String name) {
        log.info("getAllPerformanceByShowName");
        return performanceRepository.findPerformanceByShow_Name(name);
    }

    @PostMapping("/")
    public Performance createPerformance(@Valid @RequestBody PerformanceDTO performanceDTO){
        log.info("createPerformance");
        Show show = showRepository.findById(performanceDTO.getShow()).get();
        Performance performance = performanceRepository.save(Performance.builder()
                .show(show)
                .status(performanceDTO.getStatus())
                .date(performanceDTO.getDate())
                .remainingSeats(performanceDTO.getRemainingSeats())
                .streamingURL(performanceDTO.getStreamingURL())
                .build());
        return performanceRepository.save(performance);
    }

    @DeleteMapping("/{performanceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Boolean> deletePerformance(@PathVariable("performanceId") Long performanceId)
            throws ResourceNotFoundException {
        log.info("deleteCategory");
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + performanceId));
        performanceRepository.delete(performance);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
