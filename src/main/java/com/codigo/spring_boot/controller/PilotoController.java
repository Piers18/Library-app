package com.codigo.spring_boot.controller;

import com.codigo.spring_boot.entity.PilotoEntity;
import com.codigo.spring_boot.repository.PilotoRepository;
import com.codigo.spring_boot.response.ResponseBase;
import com.codigo.spring_boot.service.PilotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/piloto")
public class PilotoController {
    private PilotoRepository pilotoRepository;
    private PilotoService pilotoService;

    public PilotoController(PilotoRepository pilotoRepository, PilotoService pilotoService) {
        this.pilotoRepository = pilotoRepository;
        this.pilotoService = pilotoService;
    }

    @PostMapping("/save")
    public ResponseBase save(@RequestParam String dni){
        return  pilotoService.createPiloto(dni);
    }

    @GetMapping("/getAll")
    public List<PilotoEntity> getAll(){
        List<PilotoEntity> pilotos = pilotoRepository.findAll();
        return pilotos;
    }

    @GetMapping("/find/{id}")
    public PilotoEntity findById(@PathVariable int id){
        Optional<PilotoEntity> pilotoOptional = pilotoRepository.findById(id);

        if(pilotoOptional.isPresent()){
            PilotoEntity pilotoBd = pilotoOptional.get();
            return pilotoBd;
        }

        return null;
    }


}
