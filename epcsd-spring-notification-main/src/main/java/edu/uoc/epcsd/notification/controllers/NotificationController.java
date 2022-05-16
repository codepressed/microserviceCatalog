package edu.uoc.epcsd.notification.controllers;

import edu.uoc.epcsd.notification.pojos.Show;
import edu.uoc.epcsd.notification.services.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping(path = "/sendmessage")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestParam Show show){
        notificationService.notifyShowCreation(show);
    }



}
