package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.config.SecurityConfig;
import com.brow.caloriescalc.dto.AuthDto;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registerUser_WhenValidInput_Returns200() throws Exception {
        AuthDto userDto = new AuthDto("testname", "testpass");
        String userJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userService, times(1)).createUser(any(AuthDto.class));
    }

    @Test
    public void registerUser_WhenUsernameAlreadyExists_Returns409() throws Exception {
        AuthDto userDto = new AuthDto("existingUsername", "password");
        String userJson = objectMapper.writeValueAsString(userDto);

        when(userService.getByUsername("existingUsername")).thenReturn(new User());

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isConflict())
                .andDo(print());

        verify(userService, times(1)).getByUsername("existingUsername");
    }
}
