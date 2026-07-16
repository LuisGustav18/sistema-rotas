package com.luis.sistema_rotas.security;

import com.luis.sistema_rotas.domain.usuario.entity.Usuario;
import com.luis.sistema_rotas.domain.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        var token = this.recoverToken(request);
        if (token != null){
            var login = tokenService.validateToken(token);
            Usuario usuario = usuarioRepository.findByEmail(login).orElse(null);
            if (usuario != null){
                UserDetails user = new User(usuario.getId(), usuario.getEmail(), usuario.getSenha());
                var authentication = new UsernamePasswordAuthenticationToken(user, null , user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "")
    }
}
