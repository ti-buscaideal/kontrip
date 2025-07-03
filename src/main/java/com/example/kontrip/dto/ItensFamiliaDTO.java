package com.example.kontrip.dto;

import lombok.Data;

@Data
public class ItensFamiliaDTO {

    private String descricao;
    
    private FamiliaCiaAereaDTO familiaCiaAerea;
    
    private Integer itensFamiliaId;
    
    private String nome;
    
    private String nomeIcone;
    
    private Integer ordem;
}