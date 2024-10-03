package br.com.michelly.tcc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.michelly.tcc.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import br.com.michelly.tcc.model.Conta;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String login(String email, String senha) {
        Optional<Conta> usuarioOp = usuarioRepository.findByEmail(email);

        if (usuarioOp.isPresent()) {
            Conta usuario = usuarioOp.get();

            if (usuario.getSenha().equals(senha)) {
                return "home";
            } else {
                return "Senha incorreta!";
            }
        } else {
            return "Usuário não encontrado"; 
        }
    }

    public void registrarUsuario(String nome, String email, String telefone, String cpf, String deficiente, String datanas, String genero, String senha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datanas, formatter);

        Conta user = new Conta();
        user.setNome(nome);
        user.setEmail(email);
        user.setTelefone(telefone);
        user.setCpf(cpf);
        if (deficiente.equals("sim")) {
            user.setDeficiente(true);
        } else {
            user.setDeficiente(false);
        }
        user.setDatanas(date);
        user.setGenero(genero);
        user.setSenha(senha);

        usuarioRepository.save(user);
    }
}
