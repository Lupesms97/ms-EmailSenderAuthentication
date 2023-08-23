package api.authenticaction.emailsender.controller;

import api.authenticaction.emailsender.dto.AuthenticationDto;
import api.authenticaction.emailsender.dto.LoginResponseDto;
import api.authenticaction.emailsender.dto.RegisterDto;
import api.authenticaction.emailsender.model.UserModel;
import api.authenticaction.emailsender.repositories.UserRepository;
import api.authenticaction.emailsender.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((UserModel) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if(this.userRepository.findByLogin(data.login())!=null) return ResponseEntity.badRequest().build();

        String encryptedPassword  = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.login(), encryptedPassword,data.role());

        this.userRepository.save(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);



    }

}
