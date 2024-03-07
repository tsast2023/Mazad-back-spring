package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.DemandesSuperAdmin;

import java.util.List;

public interface IServiceDemande {
    DemandesSuperAdmin DemandeDesactivation(String categoryId, String adminId);
    List<DemandesSuperAdmin> getAll();
}
