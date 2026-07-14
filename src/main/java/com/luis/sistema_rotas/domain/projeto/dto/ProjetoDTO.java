package com.luis.sistema_rotas.domain.projeto.dto;

import com.luis.sistema_rotas.domain.projeto.entity.Projeto;

import java.util.Date;
import java.util.UUID;

public record ProjetoDTO(String titulo, Date data, UUID usuario) {

    public ProjetoDTO(Projeto projeto) {
        this(projeto.getTitulo(), projeto.getData(), projeto.getId());
    }
}
