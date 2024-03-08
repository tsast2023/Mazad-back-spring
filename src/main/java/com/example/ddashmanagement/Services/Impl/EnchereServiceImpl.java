package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Dto.EnchereRequest;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Repository.EnchereRepository;
import com.example.ddashmanagement.Services.IEnchereService;

public class EnchereServiceImpl implements IEnchereService {

    private final EnchereRepository enchereRepository ;

    public EnchereServiceImpl(EnchereRepository enchereRepository) {
        this.enchereRepository = enchereRepository;
    }

    @Override
    public Enchere CreateEnchereBrouillon(EnchereRequest e) {
        Enchere enchere = new Enchere();
        enchere.setVille(e.getVille());
        enchere.setPrixMagasin(e.getPrixMagasin());
        enchere.setPrixMazedOnline(e.getPrixMazedOnline());
        enchere.setProduct(e.getProducts());
        return enchereRepository.save(enchere);
    }
}
