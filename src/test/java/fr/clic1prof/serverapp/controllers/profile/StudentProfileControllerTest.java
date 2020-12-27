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
public class StudentProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    @Qualifier("UserProfileControllerTest")
    private UserProfileControllerTest controller;

    @Autowired
    public void login() throws Exception {
        this.controller.login("test9.student@test.com", "UnRenard60**");
    }

    @Test
    public void test_updateFirstName() throws Exception {
        this.controller.test_updateFirstName("test9.student@test.com", "UnRenard60**", "/student/profile");
    }

    @Test
    public void test_updateLastName() throws Exception {
        this.controller.test_updateLastName("test9.student@test.com", "UnRenard60**", "/student/profile");
    }

    @Test
    public void test_updatePassword() throws Exception {
        this.controller.test_updatePassword("test9.student@test.com", "UnRenard60**", "/student/profile");
    }

    @Test
    public void test_updatePicture() throws Exception {
        this.controller.test_updatePicture("test9.student@test.com", "UnRenard60**", "/student/profile");
    }

    @Test
    public void test_updateSchoolLevel() throws Exception {

        String uri = "/student/profile/school-level";
        String token = this.controller.login("test9.student@test.com", "UnRenard60**");

        // With a valid id.
        ObjectNode node = this.mapper.createObjectNode();
        node.put("id", 1);

        this.mvc.perform(this.controller.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // With an invalid positive id.
        node = this.mapper.createObjectNode();
        node.put("id", 3000);

        this.mvc.perform(this.controller.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        // With an invalid negative id.
        node = this.mapper.createObjectNode();
        node.put("id", -1);

        this.mvc.perform(this.controller.getBuilder(uri, token, node))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }
}
