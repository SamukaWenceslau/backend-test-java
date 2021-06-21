package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.config.security.TokenService;
import br.com.fcamara.parkingproject.controller.dto.TokenDto;
import br.com.fcamara.parkingproject.controller.form.AuthForm;
import br.com.fcamara.parkingproject.utils.ConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ConvertTo {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<?> authenticate(AuthForm form) {
        UsernamePasswordAuthenticationToken data =
                usernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(data);
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        }catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
