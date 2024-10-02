package br.com.michelly.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.michelly.tcc.service.LoginService;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String index() {
        return "login.html";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, 
                        @RequestParam("senha") String senha, 
                        Model model) {
        String resultado = loginService.login(email, senha);

        if (resultado.equals("redirect:/home")) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", resultado);
            return "login";
        }
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro.html";
    }
    @PostMapping("/cadastro")
    public String registrarUsuario(
        @RequestParam("nome") String nome,
        @RequestParam("email") String email,
        @RequestParam("telefone") String telefone,
        @RequestParam("cpf") String cpf,
        @RequestParam("auditivo") String deficiente,
        @RequestParam("data") String datanas,
        @RequestParam("genero") String genero,
        @RequestParam("senha") String senha){
        loginService.registrarUsuario(nome, email, telefone, cpf, deficiente, datanas, genero, senha);
        return "redirect:/login";
    }
}
