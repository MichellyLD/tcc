package br.com.michelly.tcc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.michelly.tcc.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import br.com.michelly.tcc.model.Conta;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void login(String nome, String email, String telefone, String cpf, String deficiente, String datanas, String senha){
        

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datanas, formatter);
        
        //calcular idade
        int anoNas = date.getYear();
        int mesNas = date.getMonthValue();
        int diaNas = date.getDayOfMonth();
        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();
        int diaAtual = LocalDate.now().getDayOfMonth();
        int idade = anoAtual - anoNas;
        if(mesNas > mesAtual || mesNas == mesAtual && diaNas > diaAtual){
            idade--;
        }

        //campos iguais no bdo




        Conta user = new Conta();
        user.setNome(nome);
        user.setEmail(email);
        user.setTelefone(telefone);//user.setTelefone("55 77 "+telefone);
        user.setCpf(cpf);
        if (deficiente.equals("sim")) {
            user.setDeficiente(true);
        } else {
            user.setDeficiente(false);
        }
        user.setDatanas(date);
        user.setIdade(idade);
        user.setSenha(senha);

        usuarioRepository.save(user);
    }
}
