package com.luis.sistema_rotas.domain.rota.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sistema_rotas.domain.projeto.entity.Projeto;
import com.luis.sistema_rotas.domain.rota.dto.RotaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rota {

    @Id
    @GeneratedValue
    private UUID id;

    private String titulo;

    private String descricao;

    private Double longitude;

    private Double latitude;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Rota(RotaDTO objDTO) {
        titulo = objDTO.titulo();
        descricao = objDTO.descricao();
        longitude = objDTO.longitude();
        latitude = objDTO.latitude();
        data = objDTO.data();
    }
}
