package com.example.kontrip.controller;

import com.example.kontrip.aereo.ArrayOfViagemDTO;
import com.example.kontrip.dto.ApiResponse;
import com.example.kontrip.service.AereoSoapService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1/private/kontrip")
public class AereoSoapController {

    private final AereoSoapService aereoSoapService;

    public AereoSoapController(AereoSoapService aereoSoapService) {
        this.aereoSoapService = aereoSoapService;
    }

    @GetMapping
    public ResponseEntity<?> getDisponibilidade(
            @RequestParam String cia,
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam Integer adultos,
            @RequestParam Integer criancas,
            @RequestParam Integer bebes,
            @RequestParam Boolean executiva,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataIda,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataVolta) {
        ArrayOfViagemDTO viagens = aereoSoapService.getDisponibilidade(cia, origem, destino, adultos, criancas, bebes, executiva, dataIda, dataVolta);
        ApiResponse apiResponse = buildApiResponse(viagens);
        return ResponseEntity.ok().body(apiResponse);
    }

    private ApiResponse buildApiResponse(Object response) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return new ApiResponse(true, null, data, response);
    }

}
