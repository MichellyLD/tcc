package br.com.michelly.tcc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.michelly.tcc.model.Conta;
import br.com.michelly.tcc.repository.UsuarioRepository;

@Controller
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String index(){
        return "login.html";
    }
    @GetMapping("/cadastro")
    public String cadastro(){
        return "cadastro.html";
    }
    @PostMapping ("/cadastro")
    public String registrarUsuario(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("cpf") String cpf,
            @RequestParam("auditivo") String deficiente,
            @RequestParam("data") String datanas,
            @RequestParam("senha") String senha){

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
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(){
        return "home.html";
    }
    @GetMapping("/usuario")
    public String usuario(){
        return "usuario.html";
    }
}