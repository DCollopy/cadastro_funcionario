package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.objetos.Email;
import br.com.cadastro.dominio.entidade.service.LoginService;
import br.com.cadastro.web.converte.LoginMapperWeb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    private final LoginMapperWeb loginMapperWeb;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public LoginController(LoginService loginService, LoginMapperWeb loginMapperWeb, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.loginService = loginService;
        this.loginMapperWeb = loginMapperWeb;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @PostMapping
    public ResponseEntity login(@RequestParam String email, @RequestParam String senha) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity("Sucesso - " + loginService.buscaPorEmail(email) , new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestParam String cpf, @RequestParam String email, @RequestParam String senha, @RequestParam String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Login> login = List.of(new Login(
                 new Cpf(cpf)
                ,new Email(email)
                ,encoder.encode(senha)
                ,role));

        loginService.salva(login.get(0));
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
