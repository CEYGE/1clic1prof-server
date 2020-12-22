package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.ServerTestConfiguration;
import fr.clic1prof.serverapp.dao.IUserDAO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.SQLDataException;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = ServerTestConfiguration.class)
public class UserDAOTest {

    @Autowired
    private IUserDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_findByUsername() {

        Optional<UserModel> optional1 = this.dao.findByUsername("test.student@test.com");
        Optional<UserModel> optional2 = this.dao.findByUsername("test.teacher@test.com");

        Optional<UserModel> optional3 = this.dao.findByUsername("not.present@gmail.com");
        Optional<UserModel> optional4 = this.dao.findByUsername("invalid");

        Assertions.assertTrue(optional1.isPresent(), "User 'test.student@test.com' not found.");
        Assertions.assertTrue(optional2.isPresent(), "User 'test.teacher@test.com' found.");

        Assertions.assertFalse(optional3.isPresent(), "User 'not.present@gmail.com' found.");
        Assertions.assertFalse(optional4.isPresent(), "User 'invalid' found.");
    }

    @Test
    public void test_findByUsernameModel() {

        Optional<UserModel> optional1 = this.dao.findByUsername("test.student@test.com");
        Optional<UserModel> optional2 = this.dao.findByUsername("test.teacher@test.com");

        // Checking that data exist.
        Assertions.assertTrue(optional1.isPresent(), "User 'test.student@test.com' not found.");
        Assertions.assertTrue(optional2.isPresent(), "User 'test.teacher@test.com' not found.");

        UserModel model1 = optional1.get();
        UserModel model2 = optional2.get();

        // Checking ids.
        Assertions.assertTrue(model1.getId() >= 0, "Invalid id.");
        Assertions.assertTrue(model2.getId() >= 0, "Invalid id.");

        // Checking username (= email).
        Assertions.assertEquals(model1.getUsername(), "test.student@test.com", "Invalid username.");
        Assertions.assertEquals(model2.getUsername(), "test.teacher@test.com", "Invalid username.");

        // Checking password.
        Assertions.assertEquals(model1.getPassword(), "$2a$10$0JtkkLPPoC9hcEDFRFQViOqjPe/6JYNv30DnvHHS9iYVYiqjRbLGm", "Invalid password");
        Assertions.assertEquals(model2.getPassword(), "$2a$10$0JtkkLPPoC9hcEDFRFQViOqjPe/6JYNv30DnvHHS9iYVYiqjRbLGm", "Invalid password");

        // Checking roles.
        Assertions.assertEquals(model1.getRoles(), Collections.singletonList(UserRole.STUDENT), "Invalid roles.");
        Assertions.assertEquals(model2.getRoles(), Collections.singletonList(UserRole.TEACHER), "Invalid roles.");
    }

    @Test
    public void test_registerNotRegistered() {

        Name firstName = new Name("James"), lastName = new Name("Bond");
        Email email = new Email("james.bond@mi6.uk");
        Password password = new Password("JamesBond007**");

        Registration registration = new Registration(firstName, lastName, email, password, RegistrationType.STUDENT);
        registration.setEncodedPassword(this.encoder.encode(password.getPassword()));

        this.dao.register(registration);

        Optional<UserModel> optional = this.dao.findByUsername(email.getValue());

        Assertions.assertTrue(optional.isPresent(), "User not found.");

        UserModel model = optional.get();

        Assertions.assertTrue(model.getId() >= 0, "Invalid id.");
        Assertions.assertEquals(model.getUsername(), email.getValue(), "Invalid email.");
        Assertions.assertEquals(model.getRoles(), Collections.singletonList(UserRole.STUDENT), "Invalid roles.");

        Assert.hasText(model.getPassword(), "Invalid password."); // Using specific assert for password.
    }

    @Test
    @Rollback
    public void test_registerAlreadyRegistered() {

        Registration registration = new Registration(
                new Name("James"),
                new Name("Bond"),
                new Email("james.bond@mi6.uk"),
                new Password("JamesBond007**"),
                RegistrationType.STUDENT);

        registration.setEncodedPassword(this.encoder.encode("JamesBond007**"));

        this.dao.register(registration);

        Assertions.assertThrows(DuplicateKeyException.class, () -> this.dao.register(registration));
    }
}
