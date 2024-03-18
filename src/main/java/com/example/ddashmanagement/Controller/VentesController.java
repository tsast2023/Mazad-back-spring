package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.Ventes;
import com.example.ddashmanagement.Services.IVentesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ventes")
public class VentesController {
    private final IVentesService iVentesService ;

    public VentesController(IVentesService iVentesService) {
        this.iVentesService = iVentesService;
    }

    @GetMapping("/toprated")
    public List<Ventes> getTopRatedSales() {
        return iVentesService.getTopRatedSales();
    }

}
