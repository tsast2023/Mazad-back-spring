package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Entites.Product;

import java.util.List;

public interface IServiceDemande {
    DemandesSuperAdmin DemandeDesactivationCategory(String categoryId, String adminId);
    List<DemandesSuperAdmin> getAll();

    DemandesSuperAdmin DemandeModificationProduct(String productId, String adminId , Product p);
}
