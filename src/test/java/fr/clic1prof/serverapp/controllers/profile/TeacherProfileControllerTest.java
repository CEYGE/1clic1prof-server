package fr.clic1prof.serverapp.controllers.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class TeacherProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    @Qualifier("UserProfileControllerTest")
    private UserProfileControllerTest controller;

    @Autowired
    public void login() throws Exception {
        this.controller.login("test10.teacher@test.com", "UnRenard60**");
    }

    @Test
    public void test_updateFirstName() throws Exception {
        this.controller.test_updateFirstName("/teacher/profile");
    }

    @Test
    public void test_updateLastName() throws Exception {
        this.controller.test_updateLastName("/teacher/profile");
    }

    @Test
    public void test_updatePassword() throws Exception {
        this.controller.test_updatePassword("/teacher/profile");
    }

    @Test
    public void test_updatePicture() throws Exception {
        this.controller.test_updatePicture("/teacher/profile");
    }

    @Test
    public void test_updateDescription() throws Exception {

        String uri = "/teacher/profile/description";

        // With a valid description.
        ObjectNode node = this.mapper.createObjectNode();
        node.put("description", "This is my new description.");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // With an empty description.
        node = this.mapper.createObjectNode();
        node.put("description", "");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Without description.
        this.mvc.perform(this.controller.getBuilder(uri, null))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // With a too long description.
        node = this.mapper.createObjectNode();
        node.put("description", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_updateStudies() throws Exception {

        String uri = "/teacher/profile/studies";

        // With a valid description.
        ObjectNode node = this.mapper.createObjectNode();
        node.put("studies", "Engineer in computer science");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // With an empty description.
        node = this.mapper.createObjectNode();
        node.put("studies", "");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Without description.
        this.mvc.perform(this.controller.getBuilder(uri, null))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // With a too long description.
        node = this.mapper.createObjectNode();
        node.put("studies", "poijzeaoirjezaioirjezao^rezajiriezîrzejiairezjizea^rjze^zeajrizeireziirzejze");

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_updateSpeciality() throws Exception {

        String uri = "/teacher/profile/speciality";

        // With a valid request.
        ObjectNode node = this.mapper.createObjectNode();
        node.put("toReplace", 1);
        node.put("replaceWith", 7);

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Want to replace a speciality that he doesn't own.
        node = this.mapper.createObjectNode();
        node.put("toReplace", 2);
        node.put("replaceWith", 1);

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // Want to replace an invalid speciality.
        node = this.mapper.createObjectNode();
        node.put("toReplace", 3000);
        node.put("replaceWith", 1);

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // Want to replace with an invalid speciality.
        node = this.mapper.createObjectNode();
        node.put("toReplace", 4);
        node.put("replaceWith", 3000);

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // Want to replace with a speciality already owned.
        node = this.mapper.createObjectNode();
        node.put("toReplace", 4);
        node.put("replaceWith", 4);

        this.mvc.perform(this.controller.getBuilder(uri, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }
}
