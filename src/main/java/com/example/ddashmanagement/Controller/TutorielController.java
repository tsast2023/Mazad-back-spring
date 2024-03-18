package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.Tutoriel;
import com.example.ddashmanagement.Services.ITutorielService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/tuto")
public class TutorielController {
    private final ITutorielService iTutorielService ;


    public TutorielController(ITutorielService iTutorielService) {
        this.iTutorielService = iTutorielService;
    }

    @PostMapping("/publishNow")
    public ResponseEntity<Tutoriel> publishProductNow(@RequestBody Tutoriel tutoriel) {
        Tutoriel publishedTutoriel = iTutorielService.createTuto(tutoriel);
        return ResponseEntity.ok(publishedTutoriel);
    }
    @GetMapping("/getAll")
    public List<Tutoriel> getAll(){
        return iTutorielService.getAll();
    }
    @PutMapping("/updateTuto")
    public Tutoriel updateTuto(@RequestBody Tutoriel tutoriel , @RequestParam String id) {
        return iTutorielService.updateTuto(tutoriel , id);
    }
    @DeleteMapping("/deleteTuto")
    public void deleteTuto(String id) {
        iTutorielService.deleteTuto(id);
    }
}
