package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.dto.AuthDto;
import com.brow.caloriescalc.dto.CommonResponseDto;
import com.brow.caloriescalc.model.Role;
import com.brow.caloriescalc.model.RoleEnum;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponseDto> register(@RequestBody AuthDto registerDto) {
        if (userService.getByUsername(registerDto.getUsername()) != null) {
            return new ResponseEntity<>(new CommonResponseDto("Username is taken!"), HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(registerDto.getPassword());
            user.setRole(new Role(RoleEnum.ROLE_USER));
            userService.saveUser(user);
            return new ResponseEntity<>(new CommonResponseDto("User successfully registered"), HttpStatus.OK);
        }
    }
}