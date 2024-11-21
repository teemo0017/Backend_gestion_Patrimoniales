package com.api.crud.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	

    @PostMapping(value = "login")
    public ResponseEntity<authResponse> login(@RequestBody LoginRequest request) {
    	return ResponseEntity.ok(authService.login(request));
    }
    
    

    @PostMapping(value = "register")
    public ResponseEntity<authResponse> register(@RequestBody registerRequest request) {
    	return ResponseEntity.ok(authService.register(request));
    }


}
