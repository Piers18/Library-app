package com.codigo.spring_boot.service.impl;

import com.codigo.spring_boot.entity.BoletoRequest;
import com.codigo.spring_boot.entity.PasajeroEntity;
import com.codigo.spring_boot.entity.VueloEntity;
import com.codigo.spring_boot.repository.BoletoRepository;
import com.codigo.spring_boot.repository.PasajeroRepository;
import com.codigo.spring_boot.repository.VueloRepository;
import com.codigo.spring_boot.service.BoletoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {
    private final PasajeroRepository pasajeroRepository;
    private final VueloRepository vueloRepository;
    private final BoletoRepository boletoRepository;

    public BoletoServiceImpl(PasajeroRepository pasajeroRepository, VueloRepository vueloRepository, BoletoRepository boletoRepository) {
        this.pasajeroRepository = pasajeroRepository;
        this.vueloRepository = vueloRepository;
        this.boletoRepository = boletoRepository;
    }

    @Override
    public BoletoRequest createBoleto(BoletoRequest request) {
        PasajeroEntity pasajeroRequest = request.getPasajeroEntity();
        VueloEntity vueloRequest = request.getVueloEntity();

        if(pasajeroRequest == null || vueloRequest == null){
            return null;
        }

        int pasajeroId = pasajeroRequest.getId();
        int vueloId = vueloRequest.getId();

        Optional<PasajeroEntity> optionalPasajeroBd = pasajeroRepository.findById(pasajeroId);
        Optional<VueloEntity> optionalVueloBd = vueloRepository.findById(vueloId);

        if(optionalVueloBd.isEmpty() || optionalVueloBd.isEmpty()){
            return null;
        }

        BoletoRequest boletoBdSave = new BoletoRequest();
        boletoBdSave.setAsiento(request.getAsiento());
        boletoBdSave.setClase(request.getClase());
        boletoBdSave.setPuertaEmbarque(request.getPuertaEmbarque());

        VueloEntity vueloBd = optionalVueloBd.get();
        PasajeroEntity pasajeroBd = optionalPasajeroBd.get();

        boletoBdSave.setVueloEntity(vueloBd);
        boletoBdSave.setPasajeroEntity(pasajeroBd);

        boletoRepository.save(boletoBdSave);

        return null;
    }
}
