package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.TypedeDemande;
import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Repository.DemandeRepository;
import com.example.ddashmanagement.Services.IServiceDemande;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService implements IServiceDemande {

    private final DemandeRepository demandeRepository ;


    public DemandeService(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }


    @Override
    public DemandesSuperAdmin DemandeDesactivation(String categoryId, String adminId) {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setAdminId(adminId);
        request.setCategoryId(categoryId);
        request.setTypeDemande(TypedeDemande.DemandeDesactivation);
        return demandeRepository.save(request);
    }

    @Override
    public List<DemandesSuperAdmin> getAll() {
        return demandeRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
