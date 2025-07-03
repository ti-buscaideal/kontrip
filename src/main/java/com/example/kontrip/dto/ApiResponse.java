package com.example.kontrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean sucesso;

    private String mensagem;

    private String data;

    private Object dados;
}
