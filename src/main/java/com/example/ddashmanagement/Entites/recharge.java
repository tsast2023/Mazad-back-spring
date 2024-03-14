package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.TypeRecharge;
import org.springframework.data.annotation.CreatedDate;
import java.time.Instant;

public class recharge {
    private String id ;
    private TypeRecharge typeRecharge ;

    @CreatedDate
    private Instant createdAt;


}
