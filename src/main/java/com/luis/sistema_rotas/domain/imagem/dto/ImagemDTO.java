package com.luis.sistema_rotas.domain.imagem.dto;

import com.luis.sistema_rotas.domain.imagem.entity.Imagem;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ImagemDTO(MultipartFile imagem, UUID rota) {

    public ImagemDTO(Imagem obj){
        this (null, obj.getRota().getId());
    }
}
