package com.luis.sistema_rotas.domain.rota.service;

import com.luis.sistema_rotas.domain.projeto.entity.Projeto;
import com.luis.sistema_rotas.domain.projeto.service.ProjetoService;
import com.luis.sistema_rotas.domain.rota.dto.RotaDTO;
import com.luis.sistema_rotas.domain.rota.entity.Rota;
import com.luis.sistema_rotas.domain.rota.repository.RotaRepository;
import com.luis.sistema_rotas.exceptions.DataIntegrityViolationException;
import com.luis.sistema_rotas.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RotaService {

    @Autowired
    private RotaRepository repository;

    @Autowired
    private ProjetoService projetoService;

    public Rota findById(UUID id){
        Optional<Rota> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Ponto não encontrado"));
    }

    public List<Rota> findRotaByProjetoId(UUID id){
        List<Rota> list = repository.findRotaByProjetoId(id);

        if (list.isEmpty()){
            throw new ObjectNotFoundException("Pontos não marcados");
        }

        return list;
    }

    public Rota create(RotaDTO objDTO){

        validationDuplicationSameLocation(objDTO.latitude(), objDTO.longitude());

        Rota obj = transferDataOfDTO(objDTO);

        return repository.save(obj);
    }

    public Rota update(UUID id, RotaDTO objDTO){
        Rota obj = findById(id);
        if (!obj.getLongitude().equals(objDTO.longitude()) || !obj.getLatitude().equals(objDTO.latitude())) {
            validationDuplicationSameLocation(objDTO.latitude(), objDTO.longitude());
        }
        obj = transferDataOfDTO(objDTO);
        return repository.save(obj);
    }

    public void delete(UUID id){
        Rota obj = findById(id);
        repository.delete(obj);
    }

    private void validationDuplicationSameLocation(Double latitude, Double longitude){
        if (repository.findRotaByLatitudeAndLongitude(latitude, longitude) != null){
            throw new DataIntegrityViolationException("Rota já marcada no local");
        }
    }

    public Rota transferDataOfDTO(RotaDTO objDTO){
         Projeto projeto = projetoService.findById(objDTO.projeto());

        Rota obj = new Rota();

        obj.setTitulo(objDTO.titulo());
        obj.setDescricao(objDTO.decricao());
        obj.setData(objDTO.data());
        obj.setLongitude(objDTO.longitude());
        obj.setLatitude(objDTO.latitude());
        obj.setProjeto(projeto);
        return obj;
    }
}
