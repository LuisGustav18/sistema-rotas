package com.luis.sistema_rotas.domain.projeto.service;

import com.luis.sistema_rotas.domain.projeto.dto.ProjetoDTO;
import com.luis.sistema_rotas.domain.projeto.entity.Projeto;
import com.luis.sistema_rotas.domain.projeto.repository.ProjetoRepository;
import com.luis.sistema_rotas.domain.usuario.entity.Usuario;
import com.luis.sistema_rotas.domain.usuario.service.UsuarioService;
import com.luis.sistema_rotas.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Projeto findById(UUID id){
        Optional<Projeto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Projeto não encontrado"));
    }

    public List<Projeto> findProjetoByUsuario(UUID id){
        List<Projeto> list = repository.findProjetoByUsuarioId(id);

        if (list.isEmpty()){
            throw new ObjectNotFoundException("Nenhum projeto desenvolvido");
        }

        return list;
    }

    public Projeto create(ProjetoDTO objDTO){
        Projeto obj = transferDataOfDTO(objDTO);
        return repository.save(obj);
    }

    public Projeto update(UUID id, ProjetoDTO objDTO){
        Projeto obj = findById(id);
        obj = transferDataOfDTO(objDTO);
        return repository.save(obj);
    }

    public void delete(UUID id){
        Projeto obj = findById(id);
        repository.delete(obj);
    }

    private Projeto transferDataOfDTO(ProjetoDTO objDTO){
        Projeto obj = new Projeto();

        Usuario usuario = usuarioService.findById(objDTO.usuario());

        obj.setTitulo(objDTO.titulo());
        obj.setData(objDTO.data());
        obj.setUsuario(usuario);

        return obj;
    }
}

