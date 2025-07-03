package com.example.kontrip.dto;

import lombok.Data;

@Data
public class BagagemPassageiroDTO {

    private BagagemDTO bagagem;

    private PassageiroDTO passageiro;

    private SegmentoDTO segmento;
}
