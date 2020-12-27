package fr.clic1prof.serverapp.controllers.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationRequest;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Component("UserProfileControllerTest")
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    public String login(String email, String password) throws Exception {

        AuthenticationRequest request = new AuthenticationRequest(email, password);

        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        return this.mapper.readValue(content, AuthenticationResponse.class).getToken();
    }

    public void test_updateFirstName(String email, String password, String baseURI) throws Exception {

        String uri = baseURI + "/first-name";
        String token = this.login(email, password);

        this.mvc.perform(this.getBuilder(uri, token,  new Name("John")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mvc.perform(this.getBuilder(uri, token, new Name(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("J")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("UnPrenomVraimentMaisVraimentMaisVraimentTropLong")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    public void test_updateLastName(String email, String password, String baseURI) throws Exception {

        String uri = baseURI + "/last-name";
        String token = this.login(email, password);

        this.mvc.perform(this.getBuilder(uri, token, new Name("Smith")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mvc.perform(this.getBuilder(uri, token, new Name(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("S")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mvc.perform(this.getBuilder(uri, token, new Name("UnNomVraimentMaisVraimentMaisVraimentTropLong")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    public void test_updatePassword(String email, String password, String baseURI) throws Exception {

        String uri = baseURI + "/password";
        String token = this.login(email, password);

        ObjectNode node = this.mapper.createObjectNode();

        node.put("oldPassword", "UnRenard60**");
        node.put("newPassword", "UnRenard60**");

        this.mvc.perform(this.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        node = this.mapper.createObjectNode();
        node.put("oldPassword", "UnRenard60");
        node.put("newPassword", "UnRenard60**");

        this.mvc.perform(this.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        node = this.mapper.createObjectNode();
        node.put("oldPassword", "UnRenard60**");
        node.put("newPassword", "AnInvalidNewPassword");

        this.mvc.perform(this.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    public void test_updatePicture(String email, String password, String baseURI) throws Exception {

        String uri = baseURI + "/picture";
        String token = this.login(email, password);

        // With a png file.
        File png = ResourceUtils.getFile("classpath:tests/cookie_picture.png");
        Resource resourcePng = new FileSystemResource(png);

        this.mvc.perform(this.getFileBuilder(uri, token, "cookie_picture.png", MediaType.IMAGE_PNG, resourcePng))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // With a correct png file but a bad MediaType
        this.mvc.perform(this.getFileBuilder(uri, token, "cookie_picture.png", MediaType.TEXT_PLAIN, resourcePng))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // With a jpeg file.
        File jpg = ResourceUtils.getFile("classpath:tests/cookie_picture.png");
        Resource resourceJpg = new FileSystemResource(jpg);

        this.mvc.perform(this.getFileBuilder(uri, token, "background_picture.jpg", MediaType.IMAGE_JPEG, resourceJpg))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // With a text file.
        File txt = ResourceUtils.getFile("classpath:tests/cookie_picture.png");
        Resource resourceTxt = new FileSystemResource(txt);

        this.mvc.perform(this.getFileBuilder(uri, token, "text.txt", MediaType.TEXT_PLAIN, resourceTxt))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // With a text file with the png extension.
        File falsePng = ResourceUtils.getFile("classpath:tests/false_picture.png");
        Resource falseResourcePng = new FileSystemResource(falsePng);

        this.mvc.perform(this.getFileBuilder(uri, token, "false_picture.png", MediaType.IMAGE_PNG, falseResourcePng))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    public MockHttpServletRequestBuilder getFileBuilder(String uri, String token, String file, MediaType type, Resource resource) throws IOException {

        MockMultipartFile multipart = new MockMultipartFile("picture", file, String.valueOf(type), resource.getInputStream());

        return MockMvcRequestBuilders.multipart(uri)
                .file(multipart)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.IMAGE_PNG)
                .with(request -> { request.setMethod("PUT"); return request; });
    }

    public MockHttpServletRequestBuilder getBuilder(String uri, String token, Object object) throws JsonProcessingException {
        return MockMvcRequestBuilders.put(uri)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(object));
    }
}
