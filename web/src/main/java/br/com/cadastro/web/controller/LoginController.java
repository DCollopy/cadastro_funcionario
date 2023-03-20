package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.objetos.Email;
import br.com.cadastro.dominio.entidade.service.LoginService;
import br.com.cadastro.web.converte.LoginMapperWeb;
import br.com.cadastro.web.model.LoginDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    private final LoginMapperWeb loginMapperWeb;


    public LoginController(LoginService loginService, LoginMapperWeb loginMapperWeb) {
        this.loginService = loginService;
        this.loginMapperWeb = loginMapperWeb;
    }

    @GetMapping("/buscar/{cpf}")
    public LoginDTO login(@PathVariable String cpf) {
        return loginMapperWeb.converteLoginParaLoginDTO(loginService.buscaPorCpf(cpf));
    }

    @GetMapping("/buscar/email")
    public LoginDTO buscarPorEmail(@RequestBody String email) {
        return loginMapperWeb.converteLoginParaLoginDTO(loginService.buscaPorEmail(email));
    }

    @GetMapping()
    public ResponseEntity login(@RequestParam String email, @RequestParam String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean valida =  encoder.matches(senha, loginService.buscaPorEmail(email).getSenha());
        if(valida){
            return new ResponseEntity("Sucesso - " + loginService.buscaPorEmail(email) , new HttpHeaders(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestParam String cpf, @RequestParam String email, @RequestParam String senha, @RequestParam String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Login> login = List.of(new Login(
                 new Cpf(cpf)
                ,new Email(email)
                ,encoder.encode(senha)
                ,role));
        loginService.salva(login.get(0));
    }

}
