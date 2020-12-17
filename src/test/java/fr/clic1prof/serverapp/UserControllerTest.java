package fr.clic1prof.serverapp;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import fr.clic1prof.serverapp.security.jwt.JwtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

// Useful to avoid conflicts.
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class UserControllerTest {

    @Autowired
    @Qualifier("UserDAOImpl")
    private UserDAO dao;

    @Test
    public void testLogin() {

        RestTemplate template = new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .build();

        String username = "clement.gho@outlook.fr";
        String password = "UnRenard60**";

        JwtRequest request = new JwtRequest(username, password);

        ResponseEntity<JwtResponse> entity = template.postForEntity("/login", request, JwtResponse.class);

        JwtResponse response = entity.getBody();

        assertThat(entity.getStatusCode() == HttpStatus.OK);
        assertThat(response != null); // Response cannot be null here.
        assertThat(response.getToken() != null);
    }
}
