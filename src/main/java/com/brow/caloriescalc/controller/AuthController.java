package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.dto.AuthDto;
import com.brow.caloriescalc.dto.CommonResponseDto;
import com.brow.caloriescalc.dto.JwtAuthenticationResponse;
import com.brow.caloriescalc.jwt.JWTProvider;
import com.brow.caloriescalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponseDto> register(@RequestBody AuthDto registerDto) {
        if (userService.getByUsername(registerDto.getUsername()) != null) {
            return new ResponseEntity<>(new CommonResponseDto("Username is taken!"), HttpStatus.CONFLICT);
        } else {
            userService.createUser(registerDto);
            return new ResponseEntity<>(new CommonResponseDto("User successfully registered"), HttpStatus.OK);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody AuthDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);

            return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("wrong credentials");
        }

    }
}
