package com.luis.sistema_rotas.domain.rota.dto;

import com.luis.sistema_rotas.domain.rota.entity.Rota;

import java.util.Date;
import java.util.UUID;

public record RotaDTO(String titulo, String decricao, Double longitude, Double latitude, UUID projeto, Date data) {

    public RotaDTO(Rota obj){
        this (obj.getTitulo(), obj.getDescricao(), obj.getLongitude(), obj.getLatitude(), obj.getId(), obj.getData());
    }
}
