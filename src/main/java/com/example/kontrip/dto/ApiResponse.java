package com.example.kontrip.dto;

import com.example.kontrip.aereo.ResponseDisponibilidadeAereo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean sucesso;

    private String mensagem;

    private String data;

    private ResponseDisponibilidadeAereo dados;
}
