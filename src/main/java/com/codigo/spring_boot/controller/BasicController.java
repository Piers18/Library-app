package com.codigo.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/hello-server")
    public String hello(){
        return  "Hello World";
    }

    @GetMapping("/adios-server")
    public String adios(){
        return "Adios";
    }

    @GetMapping("/mi-nombre")
    public String myName(){
        return "Me llamo Piero";
    }

}
