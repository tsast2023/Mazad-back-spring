package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Entites.Ventes;
import com.example.ddashmanagement.Repository.VentesRepository;
import com.example.ddashmanagement.Services.IVentesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteServiceIpml implements IVentesService {
    private final VentesRepository ventesRepository ;

    public VenteServiceIpml(VentesRepository ventesRepository) {
        this.ventesRepository = ventesRepository;
    }

    @Override
    public List<Ventes> getTopRatedSales() {
        return ventesRepository.findTop20ByOrderByAvis_EvaluationDescCreatedAtDesc(5);
    }
}
