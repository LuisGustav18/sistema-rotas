package com.luis.sistema_rotas.domain.imagem.dto;

import com.luis.sistema_rotas.domain.imagem.entity.Imagem;

import java.util.UUID;

public record ImagemResponseDTO(UUID id, String url, UUID rota) {

    public ImagemResponseDTO(Imagem obj){
        this (
                obj.getId(),
                obj.getUrl(),
                obj.getRota().getId()
        );
    }
}
