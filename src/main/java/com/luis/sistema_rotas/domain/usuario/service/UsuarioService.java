package com.luis.sistema_rotas.domain.usuario.service;

import com.luis.sistema_rotas.domain.usuario.dto.UsuarioDTO;
import com.luis.sistema_rotas.domain.usuario.entity.Usuario;
import com.luis.sistema_rotas.domain.usuario.repository.UsuarioRepository;
import com.luis.sistema_rotas.exceptions.DataIntegrityViolationException;
import com.luis.sistema_rotas.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario findById(UUID id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public Usuario create(UsuarioDTO objDTO){
        Usuario obj = new Usuario(objDTO);
        validationEmail(obj.getEmail());
        obj.setSenha(encoder.encode(obj.getSenha()));
        return repository.save(obj);
    }

    public Usuario update(UUID id, UsuarioDTO objDTO){
        Usuario obj = findById(id);

        if (!objDTO.email().equals(obj.getEmail())){
            validationEmail(objDTO.email());
            obj.setEmail(objDTO.email());
        }

        if (!objDTO.senha().equals(obj.getSenha())){
            obj.setSenha(encoder.encode(objDTO.senha()));
        }

        return repository.save(obj);
    }

    public void delete(UUID id){
        Usuario obj = findById(id);
        repository.delete(obj);
    }

    private void validationEmail(String email){
        Optional<Usuario> obj = repository.findByEmail(email);
        if (obj.isPresent()){
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }
}
