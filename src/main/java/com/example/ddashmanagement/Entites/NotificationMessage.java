package com.example.ddashmanagement.Entites;

import lombok.Data;

import java.util.Map;

@Data
public class NotificationMessage {
    private String recipentToken ;
    private String  title ;
    private String body ;
    private String imageUser ;
    private Map<String , String> Data ;
}
