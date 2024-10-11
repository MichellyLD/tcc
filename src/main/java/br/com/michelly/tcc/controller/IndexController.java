package br.com.michelly.tcc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.michelly.tcc.model.Conta;
import br.com.michelly.tcc.repository.UsuarioRepository;
import br.com.michelly.tcc.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
                        Model model, HttpServletRequest request) {
        String resultado = loginService.login(email, senha);

        if ("usuario".equals(resultado)) {
            request.getSession().setAttribute("email", email);
            return "redirect:/usuario";
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
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario")
    public String perfilUsuario(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email != null) {
            Optional<Conta> usuarioOp = usuarioRepository.findByEmail(email);

            if (usuarioOp.isPresent()) {
                Conta usuario = usuarioOp.get();

                model.addAttribute("nome", usuario.getNome());
                model.addAttribute("email", usuario.getEmail());
                model.addAttribute("telefone", usuario.getTelefone());
                model.addAttribute("cpf", usuario.getCpf());
                model.addAttribute("deficiente", usuario.getDeficiente());
                model.addAttribute("datanas", usuario.getDatanas());
                model.addAttribute("genero", usuario.getGenero());

                return "usuario";
            }
        }
        return "redirect:/login"; 
    }
}
