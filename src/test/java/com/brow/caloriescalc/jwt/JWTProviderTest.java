package com.brow.caloriescalc.jwt;

import com.brow.caloriescalc.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JWTProviderTest {


    private JWTProvider jwtProvider;

    private User user;

    private Authentication authentication;


    @BeforeEach
    void setup() {
        jwtProvider = new JWTProvider();
        user = new User(
                "browki",
                "strongpassword"
        );
        authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    }

    @Test
    public void shouldReturnGeneratedToken() {
        String token = jwtProvider.generateToken(authentication);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void shouldPassValidation() {
        String token = jwtProvider.generateToken(authentication);
        System.out.println(token);
        assertTrue(jwtProvider.validateToken(token));
    }

    @Test
    public void usernamesFromUserEntity_andJwtShouldMatch() {
        String token = jwtProvider.generateToken(authentication);

        String usernameFromUserEntity = user.getEmail();
        String usernameFromJwt = jwtProvider.getUsernameFromJWT(token);

        assertEquals(usernameFromUserEntity, usernameFromJwt);
    }
}
