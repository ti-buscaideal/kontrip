package com.example.kontrip.dto;

import lombok.Data;

@Data
public class ViagemDTO {

    private String ciaEmissora;

    private Boolean isentarTaxaDU;

    private Boolean maisBarato;

    private String promoCodeUtilizado;

    private ArrayOfTrechoDisponibilidadeDTO trechos;

    private ValorMoedaCambioDTO valorTotal;
}
