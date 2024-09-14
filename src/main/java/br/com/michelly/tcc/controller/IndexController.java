package br.com.michelly.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.michelly.tcc.service.LoginService;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

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
        loginService.login(nome, email, telefone, cpf, deficiente, datanas, senha);

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