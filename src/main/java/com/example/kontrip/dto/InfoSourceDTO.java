package com.example.kontrip.dto;

import lombok.Data;

import javax.xml.datatype.Duration;

@Data
public class InfoSourceDTO {

    private String status;

    private Duration tempoAberturaSessao;

    private Duration tempoExecucaoTotal;

    private String tipo;
}

