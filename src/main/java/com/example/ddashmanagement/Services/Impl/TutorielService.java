package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Entites.Tutoriel;
import com.example.ddashmanagement.Repository.TutorielRepository;
import com.example.ddashmanagement.Services.ITutorielService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TutorielService  implements ITutorielService {
    private final TutorielRepository tutorielRepository;
    @Override
    public Tutoriel createTuto(Tutoriel t) {
        Tutoriel tuto = new Tutoriel();
        tuto.setFile(t.getFile());
        tuto.setDescription(t.getDescription());
        tuto.setOrdre(t.getOrdre());
        return tutorielRepository.save(tuto);
    }

    @Override
    public List<Tutoriel> getAll() {
        return tutorielRepository.findAll();
    }

    @Override
    public Tutoriel updateTuto(Tutoriel t , String id) {
        Tutoriel tuto = tutorielRepository.findById(id).get();
        tuto.setFile(t.getFile());
        tuto.setDescription(t.getDescription());
        tuto.setOrdre(t.getOrdre());
        return tutorielRepository.save(tuto);
    }

    @Override
    public void deleteTuto(String id) {
        tutorielRepository.deleteById(id);
    }
}
