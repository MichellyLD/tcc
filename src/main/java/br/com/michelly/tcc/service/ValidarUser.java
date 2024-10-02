package br.com.michelly.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.michelly.tcc.model.Conta;
import br.com.michelly.tcc.repository.UsuarioRepository;

@Service
public class ValidarUser {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String validarUser(String email, String senha) {
        Optional<Conta> usuarioOp = usuarioRepository.findByEmail(email);
        
        if (usuarioOp.isPresent()) {
            Conta usuario = usuarioOp.get();
            if (usuario.getSenha().equals(senha)) {
                return "redirect:/home";
            } else {
                return "Senha incorreta!";
            }
        } else {
            return "Usuário não existe";
        }
    }
}
