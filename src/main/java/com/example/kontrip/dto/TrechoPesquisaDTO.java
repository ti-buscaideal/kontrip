package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class TrechoPesquisaDTO {

    private XMLGregorianCalendar dataPartida;

    private String destino;

    private com.example.kontrip.dto.PeriodoHoraDTO horaChegada;

    private com.example.kontrip.dto.PeriodoHoraDTO horaPartida;

    private String horarioPartida;

    private String origem;

    private Integer raioDistanciaMilhasDestino;

    private Integer raioDistanciaMilhasOrigem;
}
