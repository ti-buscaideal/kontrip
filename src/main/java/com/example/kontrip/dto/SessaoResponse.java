package com.example.kontrip.dto;

import com.example.kontrip.sessao.ISessao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessaoResponse {

    private ISessao sessao;

    private String guidSessao;
}
