package com.codigo.spring_boot.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
public class ResponseBase {
    private int codigo;
    private String mensaje;
    private Optional data;
}
