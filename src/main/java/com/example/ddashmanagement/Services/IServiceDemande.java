package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Entites.Product;

import java.util.List;

public interface IServiceDemande {
    DemandesSuperAdmin DemandeDesactivationCategory(String categoryId, String adminId);
    List<DemandesSuperAdmin> getAll();

    DemandesSuperAdmin DemandeModificationProduct(String productId, String adminId , Product p);

    DemandesSuperAdmin DemandeModificationEnchere(String enchereID, String adminId , Enchere e);

    DemandesSuperAdmin DemandeDesactivationProduct(String productId, String adminId);

    DemandesSuperAdmin DemandeAnnulationEnchere(String EnchereId, String adminId);
    DemandesSuperAdmin findById(String id);


}
