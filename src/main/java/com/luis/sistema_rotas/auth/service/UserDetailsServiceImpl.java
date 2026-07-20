package com.luis.sistema_rotas.auth.service;

import com.luis.sistema_rotas.domain.usuario.entity.Usuario;
import com.luis.sistema_rotas.domain.usuario.repository.UsuarioRepository;
import com.luis.sistema_rotas.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> obj = repository.findByEmail(email);
        if (obj.isPresent()){
            return new User(obj.get().getId(),
                    obj.get().getEmail(),
                    obj.get().getSenha()
            );
        }
        throw new UsernameNotFoundException(email);
    }
}
