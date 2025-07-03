package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class SegmentoDTO {

    private String aeronave;

    private ArrayOfBaseTarifariaDTO basesTarifarias;

    private CabineDTO cabine;

    private XMLGregorianCalendar chegada;

    private String classe;

    private Long codigoDSG;

    private String companhiaOperadora;

    private String companhiaVendedora;

    private String destino;

    private Integer distanciaMilhas;

    private Integer distanciaQuilometros;

    private Duration duracaoSegmento;

    private Integer escalas;

    private String numeroVoo;

    private Integer ordem;

    private String origem;

    private ArrayOfParadaDTO paradas;

    private XMLGregorianCalendar partida;

    private String refCiaAerea;

    private String textoLivre;
}
