package com.example.kontrip.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ValorMoedaCambioDTO {

    private BigDecimal cambio;
    
    private ValorMoedaDTO convertido;
    
    private ValorMoedaDTO original;
}