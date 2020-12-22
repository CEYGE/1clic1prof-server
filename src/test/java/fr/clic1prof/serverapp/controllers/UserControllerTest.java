package fr.clic1prof.serverapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import fr.clic1prof.serverapp.security.jwt.JwtResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test_successLogin() throws Exception {

        JwtRequest request = new JwtRequest("test.student@test.com", "UnRenard60**");

        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        JwtResponse response = this.mapper.readValue(content, JwtResponse.class);

        Assertions.assertNotNull(response);
        Assert.hasText(response.getToken(), "Empty token.");
    }

    @Test
    public void test_errorLoginPartialCredentials() throws Exception {

        // Without credentials.
        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // With email only.
        JwtRequest missingEmailRequest = new JwtRequest(null, "UnRenard60**");

        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(missingEmailRequest)))
                .andExpect(status().isBadRequest());

        // With password only.
        JwtRequest missingPasswordRequest = new JwtRequest("InvalidEmail", null);

        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(missingPasswordRequest)))
                .andExpect(status().isBadRequest());

        // Without email and password.
        JwtRequest missingAll = new JwtRequest(null, null);

        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(missingAll)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_errorLoginInvalidCredentials() throws Exception {

        // With bad email.
        JwtRequest request1 = new JwtRequest("InvalidEmail", "UnRenard60**");

        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(request1)))
                .andExpect(status().isUnauthorized());

        // With bad password.
        JwtRequest request2 = new JwtRequest("test.student@", "RandomPassword");

        this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(request2)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void test_successRegistration() throws Exception {

        RegistrationTest registration = new RegistrationTest("james.bond@mi6.uk", "JamesBond007**", "James", "Bond", "STUDENT");

        this.mvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(registration)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_errorRegistration() throws Exception {

        RegistrationTest registration;

        // Invalid email
        this.performBadRegistration(new RegistrationTest("james.bond", "JamesBond007**", "James", "Bond", "STUDENT"));
        this.performBadRegistration(new RegistrationTest("james.bond@", "JamesBond007**", "James", "Bond", "STUDENT"));
        this.performBadRegistration(new RegistrationTest("james.bond@mi6", "JamesBond007**", "James", "Bond", "STUDENT"));

        // Invalid password
        // A passwords need at least 1 lowercase, 1 uppercase, one digit and one special char.
        // It length must be at least 8 chars.

        // With lowercase only.
        this.performBadRegistration(new RegistrationTest("james.bond@mi6.uk", "james", "James", "Bond", "STUDENT"));

        // With uppercase only.
        this.performBadRegistration(new RegistrationTest("james.bond@mi6.uk", "JAMES", "James", "Bond", "STUDENT"));

        // With uppercase and lowercase.
        this.performBadRegistration(new RegistrationTest("james.bond@mi6.uk", "JAMES", "James", "Bond", "STUDENT"));

        // With uppercase, lowercase and digits.
        this.performBadRegistration(new RegistrationTest("james.bond@mi6.uk", "JAmes007", "James", "Bond", "STUDENT"));

        // With uppercase
        this.performBadRegistration(new RegistrationTest("james.bond@mi6.uk", "JAMES", "James", "Bond", "STUDENT"));
    }

    private void performBadRegistration(RegistrationTest registration) throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(registration)))
                .andExpect(status().isBadRequest());
    }

    private static class RegistrationTest {

        private String email, password;
        private String firstName, lastName;
        private String type;

        public RegistrationTest(String email, String password, String firstName, String lastName, String type) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.type = type;
        }

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getType() {
            return this.type;
        }
    }
}
