package com.codigo.spring_boot.controller;

import com.codigo.spring_boot.entity.VueloEntity;
import com.codigo.spring_boot.repository.VueloRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private VueloRepository vueloRepository;

    public VueloController(VueloRepository vueloRepository){
        this.vueloRepository = vueloRepository;
    }

    @GetMapping("/find/all")
    public List<VueloEntity> getAllVuelos(){
        List<VueloEntity> vuelos = vueloRepository.findAll();
        return vuelos;
    }

    @GetMapping("/find/betweenMaxOrMinDate")
    public List<VueloEntity> findVueloBetweenMaxOrMinDate(@RequestParam(required = true) Date min, @RequestParam(required = true)  Date max){
        List<VueloEntity> vuelos = vueloRepository.findVueloBetweenMaxOrMinDate(min, max);
        return vuelos;
    }
}
