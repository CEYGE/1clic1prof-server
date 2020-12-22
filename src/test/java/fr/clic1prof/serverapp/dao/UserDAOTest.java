package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.profile.Email;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.Password;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.registration.RegistrationType;
import fr.clic1prof.serverapp.model.user.UserModel;
import fr.clic1prof.serverapp.model.user.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class UserDAOTest {

    @Autowired
    private IUserDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_findByUsername() {

        Optional<UserModel> student1 = this.dao.findByUsername("test1.student@test.com");
        Optional<UserModel> teacher1 = this.dao.findByUsername("test1.teacher@test.com");

        Assertions.assertTrue(student1.isPresent());
        Assertions.assertTrue(teacher1.isPresent());

        Optional<UserModel> optional1 = this.dao.findByUsername("invalid.user@gmail.com");
        Optional<UserModel> optional2 = this.dao.findByUsername("invalid_user");

        Assertions.assertFalse(optional1.isPresent());
        Assertions.assertFalse(optional2.isPresent());
    }

    @Test
    public void test_userModel() {

        Optional<UserModel> optional1 = this.dao.findByUsername("test1.student@test.com");
        Optional<UserModel> optional2 = this.dao.findByUsername("test1.teacher@test.com");

        Assertions.assertTrue(optional1.isPresent());
        Assertions.assertTrue(optional2.isPresent());

        UserModel model1 = optional1.get();
        UserModel model2 = optional2.get();

        Assertions.assertEquals(model1.getUsername(), "test1.student@test.com");
        Assertions.assertEquals(model2.getUsername(), "test1.teacher@test.com");

        Assertions.assertEquals(model1.getPassword(), "$2a$10$0JtkkLPPoC9hcEDFRFQViOqjPe/6JYNv30DnvHHS9iYVYiqjRbLGm");
        Assertions.assertEquals(model2.getPassword(), "$2a$10$0JtkkLPPoC9hcEDFRFQViOqjPe/6JYNv30DnvHHS9iYVYiqjRbLGm");

        Assertions.assertEquals(model1.getRoles(), Collections.singletonList(UserRole.STUDENT));
        Assertions.assertEquals(model2.getRoles(), Collections.singletonList(UserRole.TEACHER));
    }
}
