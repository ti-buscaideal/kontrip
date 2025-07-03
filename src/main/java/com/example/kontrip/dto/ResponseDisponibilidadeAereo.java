package com.example.kontrip.dto;

import lombok.Data;

@Data
public class ResponseDisponibilidadeAereo {

    private ArrayOfAcordoDTO acordos;

    private ArrayOfResumoSourceDTO info;

    private String pesquisaId;

    private ArrayOfViagemDTO viagens;

}
