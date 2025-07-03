package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class PassageiroDTO {

    private String cpf;
    
    private String codigoPaisNacionalidade;
    
    private String codigoPaisResidencia;
    
    private String numeroDocumento;
    
    private PassaporteDTO passaporte;
    
    private Integer tipoDocumento;
    
    private Long codigoDSG;
    
    private String email;
    
    private XMLGregorianCalendar nascimento;
    
    private String nome;
    
    private String nomeCompleto;
    
    private String refCliente;
    
    private String refSource;
    
    private String sobrenome;
    
    private Boolean utilizarProfile;
}