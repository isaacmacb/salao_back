package com.app.salaodesobrancelhas.security;

import com.app.salaodesobrancelhas.entity.Usuario;
import com.app.salaodesobrancelhas.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca usuário no banco
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não identificado"));

        // Retorna um UserDetails com username, senha criptografada e role
        return User.withUsername(usuario.getUsername())
                .password(usuario.getSenha()) // senha deve estar criptografada com BCrypt
                .roles("USER") // ou buscar a role do banco
                .build();
    }
}
