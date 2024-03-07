package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.NotificationMessage;
import com.example.ddashmanagement.Services.Impl.FirebaseMessagingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Notification")
public class NotificationController {
    private final FirebaseMessagingService firebaseMessagingService ;

    public NotificationController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }
    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody NotificationMessage notificationMessage) {
         return firebaseMessagingService.SendNotificationByToken(notificationMessage);
    }
}
