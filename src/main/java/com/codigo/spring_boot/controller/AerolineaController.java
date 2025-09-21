package com.codigo.spring_boot.controller;

import com.codigo.spring_boot.entity.AerolineaEntity;
import com.codigo.spring_boot.repository.AerolineaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {
    private AerolineaRepository aerolineaRepository;

    public AerolineaController(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @PostMapping("/save")
    public AerolineaEntity save(@RequestBody AerolineaEntity aerolineaEntity){
        return aerolineaRepository.save(aerolineaEntity);
    }

    @GetMapping("/getAll")
    public List<AerolineaEntity> getAll(){
        List<AerolineaEntity> aerolineas = aerolineaRepository.findAll();
        return aerolineas;
    }

    @GetMapping("/find/{id}")
    public AerolineaEntity findById(@PathVariable int id){
        Optional<AerolineaEntity> aerolineaOptional = aerolineaRepository.findById(id);

        if(aerolineaOptional.isPresent()){
            AerolineaEntity aerolineaBd = aerolineaOptional.get();
            return aerolineaBd;
        }

        return null;
    }
}
