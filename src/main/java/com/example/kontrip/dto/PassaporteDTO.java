package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class PassaporteDTO {

    private String codigoPaisEmissao;
    
    private XMLGregorianCalendar dataExpiracao;
    
    private String numeroDocumento;
}