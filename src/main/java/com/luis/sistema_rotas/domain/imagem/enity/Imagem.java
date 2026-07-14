package com.luis.sistema_rotas.domain.imagem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sistema_rotas.domain.rota.entity.Rota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Imagem {

    @Id
    @GeneratedValue
    private UUID id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    private Rota rota;

    @JsonFormat(pattern = "DD/mm/yyyy")
    private LocalDate dataCriacao = LocalDate.now();
}
