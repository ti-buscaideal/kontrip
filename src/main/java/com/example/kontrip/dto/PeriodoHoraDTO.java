package com.example.kontrip.dto;

import lombok.Data;

@Data
public class PeriodoHoraDTO {

    private com.example.kontrip.dto.HorarioDTO fim;

    private com.example.kontrip.dto.HorarioDTO inicio;
}
