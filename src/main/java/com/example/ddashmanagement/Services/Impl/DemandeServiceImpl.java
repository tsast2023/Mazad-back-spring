package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.TypedeDemande;
import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Repository.DemandeRepository;
import com.example.ddashmanagement.Services.IServiceDemande;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeServiceImpl implements IServiceDemande {

    private final DemandeRepository demandeRepository ;


    public DemandeServiceImpl(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }


    @Override
    public DemandesSuperAdmin DemandeDesactivationCategory(String categoryId, String adminId) {
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

    @Override
    public DemandesSuperAdmin DemandeModificationProduct(String productId, String adminId , Product p)  {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setAdminId(adminId);
        request.setProductId(productId);
        request.setTypeDemande(TypedeDemande.DemandeModification);

        return demandeRepository.save(request);
    }

    @Override
    public DemandesSuperAdmin DemandeModificationEnchere(String enchereID, String adminId, Enchere e) {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setAdminId(adminId);
        request.setEnchereId(enchereID);
        request.setTypeDemande(TypedeDemande.DemandeModification);
        return demandeRepository.save(request);
    }

    @Override
    public DemandesSuperAdmin DemandeDesactivationProduct(String productId, String adminId) {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setAdminId(adminId);
        request.setProductId(productId);
        request.setTypeDemande(TypedeDemande.DemandeDesactivation);
        return demandeRepository.save(request);
}

    @Override
    public DemandesSuperAdmin DemandeAnnulationEnchere(String EnchereId, String adminId) {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setEnchereId(EnchereId);
        request.setAdminId(adminId);
        request.setTypeDemande(TypedeDemande.DemandeAnnulation);
        return demandeRepository.save(request);
    }

    @Override
    public DemandesSuperAdmin findById(String id) {
        return demandeRepository.findById(id).get();
    }

    @Override
    public DemandesSuperAdmin DemandeAcceptationVendeur(String userId) {
        DemandesSuperAdmin request = new DemandesSuperAdmin();
        request.setVendeurId(userId);
        request.setTypeDemande(TypedeDemande.demandeActivation);
        return demandeRepository.save(request);
    }

}
