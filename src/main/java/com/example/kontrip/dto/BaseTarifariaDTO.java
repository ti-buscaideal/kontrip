package com.example.kontrip.dto;

import lombok.Data;

@Data
public class BaseTarifariaDTO {

    private TipoPassageiroDTO aplicacaoPax;

    private Long codigoDSG;

    private String familia;

    private Boolean franquiaBagagem;

    private ArrayOfItensFamiliaDTO itensFamilia;

    private String nome;

    private String refCiaAerea;

    private String regraTarifaria;

    private ArrayOfTaxaDTO taxas;

    private ValorMoedaCambioDTO valorDU;

    private ValorMoedaCambioDTO valorDesconto;

    private ValorMoedaCambioDTO valorMarkup;

    private ValorMoedaCambioDTO valorTarifa;

    private ValorMoedaCambioDTO valorTarifaOriginal;

    private ValorMoedaCambioDTO valorTaxas;
}
