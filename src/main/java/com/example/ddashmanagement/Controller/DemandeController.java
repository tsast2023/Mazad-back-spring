package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Services.IServiceDemande;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeController {
    private final IServiceDemande iServiceDemande;

    public DemandeController(IServiceDemande iServiceDemande) {
        this.iServiceDemande = iServiceDemande;
    }

    @GetMapping("/All")
    public List<DemandesSuperAdmin> getAll(){
    return  iServiceDemande.getAll();
}


}
