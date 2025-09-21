package com.codigo.spring_boot.controller;

import com.codigo.spring_boot.response.ResponseBase;
import com.codigo.spring_boot.service.PasajeroService;
import com.codigo.spring_boot.service.PilotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pasajero")
public class PasajeroController {
    private PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }


    @PostMapping("/save")
    public ResponseBase save(@RequestParam String dni){
        return pasajeroService.crearPasajero(dni);
    }
}
