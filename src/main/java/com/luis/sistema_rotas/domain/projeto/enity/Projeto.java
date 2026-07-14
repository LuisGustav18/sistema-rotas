package com.luis.sistema_rotas.domain.projeto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sistema_rotas.domain.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue
    private UUID id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Date data;

    @JsonFormat(pattern = "DD/mm/yyyy")
    private LocalDate dataCriacao = LocalDate.now();
}
