package com.luis.sistema_rotas.domain.rota.repository;

import com.luis.sistema_rotas.domain.rota.entity.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RotaRepository extends JpaRepository<Rota, UUID> {

    List<Rota> findRotaByProjetoId(UUID id);

    Rota findRotaByLatitudeAndLongitude(Double latidude, Double longitude);
}
