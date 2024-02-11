package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.config.SecurityConfig;
import com.brow.caloriescalc.dto.UserDto;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    public void getUserById_whenUserExists_Returns200() throws Exception {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userService.getUserById(anyLong())).thenReturn(user);
        when(userService.convertToDto(user)).thenReturn(userDto);

        mockMvc.perform(get("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
