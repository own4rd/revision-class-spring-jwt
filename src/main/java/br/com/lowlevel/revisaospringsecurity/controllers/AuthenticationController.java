package br.com.lowlevel.revisaospringsecurity.controllers;

import br.com.lowlevel.revisaospringsecurity.domain.user.AuthenticationDTO;
import br.com.lowlevel.revisaospringsecurity.domain.user.LoginResponseDTO;
import br.com.lowlevel.revisaospringsecurity.domain.user.User;
import br.com.lowlevel.revisaospringsecurity.domain.user.UserRegisterDTO;
import br.com.lowlevel.revisaospringsecurity.infra.security.TokenService;
import br.com.lowlevel.revisaospringsecurity.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;


    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.login(), authenticationDTO.password()
        );
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        if(this.userRepository.findByLogin(userRegisterDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());
        User user = new User(userRegisterDTO.login(), encryptedPassword, userRegisterDTO.role());

        this.userRepository.save(user);
        return ResponseEntity.ok().build();

    }
}
