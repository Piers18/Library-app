package com.codigo.spring_boot.controller;

import com.codigo.spring_boot.entity.BoletoRequest;
import com.codigo.spring_boot.service.BoletoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boleto")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/save")
    public BoletoRequest saveBoleto(@RequestBody BoletoRequest request){
        return boletoService.createBoleto(request);
    }
}
