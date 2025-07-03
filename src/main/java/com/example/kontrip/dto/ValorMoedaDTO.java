package com.example.kontrip.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ValorMoedaDTO {

    private String siglaMoeda;
    
    private BigDecimal valor;
}