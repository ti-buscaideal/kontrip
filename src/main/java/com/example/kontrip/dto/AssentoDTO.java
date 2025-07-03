package com.example.kontrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssentoDTO {

    private List<String> caracteristicaAssento;
    
    private Boolean isAssentoConforto;
    
    private String letra;
    
    private String linhaNumero;
    
    private List<String> posicaoAssento;
    
    private List<String> statusAssento;
    
    private ValorMoedaDTO valorTaxas;
}