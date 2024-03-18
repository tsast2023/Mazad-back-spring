package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.Tutoriel;

import java.util.List;

public interface ITutorielService {
    Tutoriel createTuto(Tutoriel t) ;
    List<Tutoriel> getAll();

    Tutoriel updateTuto(Tutoriel t , String id);

    void deleteTuto(String id) ;
}
