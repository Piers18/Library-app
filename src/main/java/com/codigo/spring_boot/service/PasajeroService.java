package com.codigo.spring_boot.service;

import com.codigo.spring_boot.response.ResponseBase;

public interface PasajeroService {
    ResponseBase crearPasajero(String dni);
}
