package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class TrechoDTO {

    private ArrayOfAssentoPassageiroDTO assentos;

    private ArrayOfBagagemPassageiroDTO bagagens;

    private ArrayOfBaseTarifariaDTO basesTarifarias;

    private XMLGregorianCalendar chegada;

    private String ciaEmissora;

    private Long codigoDSG;

    private String codigoPesquisa;

    private String codigoPesquisaTrecho;

    private String destino;

    private Duration duracaoVoo;

    private Boolean isentarTaxaDU;

    private String origem;

    private XMLGregorianCalendar partida;

    private String refCiaAerea;

    private ArrayOfSegmentoDTO segmentos;

    private String siglaMoeda;

    private String source;

    private String textoLivre;

    private ValorMoedaCambioDTO valorDU;

    private ValorMoedaCambioDTO valorMarkup;

    private ValorMoedaCambioDTO valorServicos;

    private ValorMoedaCambioDTO valorTaxas;
}
