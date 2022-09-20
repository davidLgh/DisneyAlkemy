package com.alkemy.disney.disney.auth.controller;

import com.alkemy.disney.disney.auth.dto.AuthenticationRequest;
import com.alkemy.disney.disney.auth.dto.AuthenticationResponse;
import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.auth.service.AuthenticationService;
import com.alkemy.disney.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private UserDetailsCustomService userDetailsService;
    private AuthenticationService authenticationService;
    private UserRepository userRepository;
    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetailsService,
            AuthenticationService authenticationService,
            UserRepository userRepository){
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws InterruptedException, ExecutionException {
        this.userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid  @RequestBody UserDTO user) throws Exception, InterruptedException, ExecutionException {

        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception, InterruptedException, ExecutionException {
        String jwt = authenticationService.authService(authRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
