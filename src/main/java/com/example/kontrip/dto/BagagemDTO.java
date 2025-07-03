package com.example.kontrip.dto;

import lombok.Data;

@Data
public class BagagemDTO {

    private Integer numeroDeBagagens;
    
    private Integer pesoMaximo;
    
    private Integer pesoMinimo;
    
    private ValorMoedaDTO valorTaxas;
}