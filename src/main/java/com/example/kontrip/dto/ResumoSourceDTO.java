package com.example.kontrip.dto;

import lombok.Data;

@Data
public class ResumoSourceDTO {

    private InfoSourceDTO infoSource;

    private ArrayOfInfoTrechoDTO quantidadeOpcoes;

    private Integer quantidadeViagens;
}
