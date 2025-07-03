package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class ParadaDTO {

    private XMLGregorianCalendar chegada;
    
    private Long codigoDSG;
    
    private String iata;
    
    private XMLGregorianCalendar partida;
    
    private TipoParadaDTO tipo;
}