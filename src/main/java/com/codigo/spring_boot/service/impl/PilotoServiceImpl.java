package com.codigo.spring_boot.service.impl;

import com.codigo.spring_boot.dto.ReniecResponse;
import com.codigo.spring_boot.entity.PilotoEntity;
import com.codigo.spring_boot.feingClient.ReniecClient;
import com.codigo.spring_boot.repository.PilotoRepository;
import com.codigo.spring_boot.response.ResponseBase;
import com.codigo.spring_boot.service.PilotoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotoServiceImpl implements PilotoService {
    private final PilotoRepository pilotoRepository;
    private final ReniecClient reniecClient;

    @Value("${reniec.api.token}")
    private String token;

    public PilotoServiceImpl(PilotoRepository pilotoRepository, ReniecClient reniecClient) {
        this.pilotoRepository = pilotoRepository;
        this.reniecClient = reniecClient;
    }

    @Override
    public ResponseBase createPiloto(String dni) {

        try {
            ReniecResponse pilotoInfo = reniecClient.getPersona(dni, token);
            PilotoEntity pilotoEntity = new PilotoEntity();
            pilotoEntity.setNombre(pilotoInfo.getFirstName());
            pilotoEntity.setApellido(pilotoInfo.getFirstLastName());
            pilotoEntity.setDni(dni);
            pilotoEntity.setEstado(true);
            pilotoRepository.save(pilotoEntity);

            return new ResponseBase(200, "Exito", Optional.of(pilotoEntity));
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

    @Override
    public List<PilotoEntity> findAllPilotos() {
        return List.of();
    }

    @Override
    public PilotoEntity findPilotoById(Long id) {
        return null;
    }
}
