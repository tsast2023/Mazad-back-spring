package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {
    private String recipentToken ;
    private String  title ;
    private String body ;
    private String imageUser ;
    private Map<String , String> Data ;
}
