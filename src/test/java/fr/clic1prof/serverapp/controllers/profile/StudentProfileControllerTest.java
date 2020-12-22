package fr.clic1prof.serverapp.controllers.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import fr.clic1prof.serverapp.security.jwt.JwtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class StudentProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private String token;

    @Autowired
    public void login() throws Exception {

        JwtRequest request = new JwtRequest("test1.student@test.com", "UnRenard60**");

        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        this.token = this.mapper.readValue(content, JwtResponse.class).getToken();
    }

    @Test
    public void test_updateFirstName() throws Exception {

        String uri = "first-name";

        this.mvc.perform(this.getBuilder(uri, new Name("John")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mvc.perform(this.getBuilder(uri, new Name(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("J")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("UnPrenomVraimentMaisVraimentMaisVraimentTropLong")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_updateLastName() throws Exception {

        String uri = "last-name";

        this.mvc.perform(this.getBuilder(uri, new Name("Smith")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mvc.perform(this.getBuilder(uri, new Name(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("S")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, new Name("UnNomVraimentMaisVraimentMaisVraimentTropLong")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_updatePassword() throws Exception {

        String uri = "password";

        ObjectNode node = this.mapper.createObjectNode();

        node.put("oldPassword", "UnRenard60**");
        node.put("newPassword", "UnRenard60**");

        this.mvc.perform(this.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        node = this.mapper.createObjectNode();
        node.put("oldPassword", "UnRenard60");
        node.put("newPassword", "UnRenard60**");

        this.mvc.perform(this.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // TODO to change with badRequest.

        node = this.mapper.createObjectNode();
        node.put("oldPassword", "UnRenard60**");
        node.put("newPassword", "AnInvalidNewPassword");

        this.mvc.perform(this.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private MockHttpServletRequestBuilder getBuilder(String uri, Object object) throws JsonProcessingException {
        return MockMvcRequestBuilders.put("/student/profile/" + uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(object))
                .header("Authorization", "Bearer " + this.token);
    }
}
