package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.form.AuthForm;
import br.com.fcamara.parkingproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthService authenticationService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid AuthForm form) {
			return authenticationService.authenticate(form);
	}
}
