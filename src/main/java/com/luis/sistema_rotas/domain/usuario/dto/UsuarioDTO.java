package com.luis.sistema_rotas.domain.usuario.dto;

import com.luis.sistema_rotas.domain.usuario.entity.Usuario;

public record UsuarioDTO(String email, String senha) {

    public UsuarioDTO(Usuario usuario){
        this (usuario.getEmail(), usuario.getSenha());
    }
}
