package com.codigo.spring_boot.service.impl;

import com.codigo.spring_boot.dto.ReniecResponse;
import com.codigo.spring_boot.entity.PasajeroEntity;
import com.codigo.spring_boot.entity.PilotoEntity;
import com.codigo.spring_boot.feingClient.ReniecClient;
import com.codigo.spring_boot.repository.PasajeroRepository;
import com.codigo.spring_boot.response.ResponseBase;
import com.codigo.spring_boot.service.PasajeroService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {
    private final PasajeroRepository pasajeroRepository;
    private final ReniecClient reniecClient;

    @Value("${reniec.api.token}")
    private String token;

    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, ReniecClient reniecClient) {
        this.pasajeroRepository = pasajeroRepository;
        this.reniecClient = reniecClient;
    }

    @Override
    public ResponseBase crearPasajero(String dni) {
        try {
            ReniecResponse pasajeroInfo = reniecClient.getPersona(dni, token);
            PasajeroEntity pasajeroEntity = new PasajeroEntity();
            pasajeroEntity.setNombre(pasajeroInfo.getFirstName());
            pasajeroEntity.setApellido(pasajeroInfo.getFirstLastName());
            pasajeroEntity.setDni(dni);

            pasajeroRepository.save(pasajeroEntity);

            return new ResponseBase(200, "Exito", Optional.of(pasajeroEntity));
        } catch (FeignException e){
            int status = e.status();
            if(status == 404){
                return new ResponseBase(status, "DNI no encontrado", Optional.empty());
            } else if (status == 442) {
                return new ResponseBase(status, "DNI invalido", Optional.empty());
            } else if (status == 401) {
                return new ResponseBase(status, "Toke invalido", Optional.empty());
            } else {
                return new ResponseBase(status, "Time Out", Optional.empty());
            }
        }
    }
}
