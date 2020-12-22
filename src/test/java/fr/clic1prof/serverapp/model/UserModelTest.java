package fr.clic1prof.serverapp.model;

import fr.clic1prof.serverapp.model.profile.Email;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.Password;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class UserModelTest {

    @Autowired
    private Validator validator;

    @Test
    public void test_password() {

        Assertions.assertFalse(this.hasViolations(new Password("JamesBond007**"))); // Success.

        Assertions.assertTrue(this.hasViolations(new Password(null))); // Null password.
        Assertions.assertTrue(this.hasViolations(new Password(""))); // Empty password.
        Assertions.assertTrue(this.hasViolations(new Password("Ja0*"))); // Less than 8 chars.

        Assertions.assertTrue(this.hasViolations(new Password("james"))); // Lowercase only.
        Assertions.assertTrue(this.hasViolations(new Password("JAMES"))); // Uppercase only.
        Assertions.assertTrue(this.hasViolations(new Password("007"))); // Digits only.
        Assertions.assertTrue(this.hasViolations(new Password("**"))); // Special chars only.

        Assertions.assertTrue(this.hasViolations(new Password("James"))); // Lowercase and uppercase.
        Assertions.assertTrue(this.hasViolations(new Password("James007"))); // Lowercase, uppercase and digits.
        Assertions.assertTrue(this.hasViolations(new Password("james007"))); // Lowercase with digits.
        Assertions.assertTrue(this.hasViolations(new Password("JAMES007"))); // Uppercase with digits.
    }

    @Test
    public void test_email() {

        Assertions.assertFalse(this.hasViolations(new Email("james.bond@mi6.uk")));
        Assertions.assertFalse(this.hasViolations(new Email("bond@mi6.uk")));

        Assertions.assertTrue(this.hasViolations(new Email("james.bond.mi6.uk"))); // Missing @.
        Assertions.assertTrue(this.hasViolations(new Email("james.bond@mi6"))); // Missing TDL.
        Assertions.assertTrue(this.hasViolations(new Email("james.bond@mi$.uk"))); // Special char.
    }

    @Test
    public void test_name() {

        Assertions.assertFalse(this.hasViolations(new Name("Clément")));
        Assertions.assertFalse(this.hasViolations(new Name("Jean-Mickael")));

        Assertions.assertTrue(this.hasViolations(new Name(null)));
        Assertions.assertTrue(this.hasViolations(new Name("")));
        Assertions.assertTrue(this.hasViolations(new Name("JeMappelleJeanMickaelEtMonPrenomEstVraimentTresLong"))); // More than 32 chars.
        Assertions.assertTrue(this.hasViolations(new Name("C"))); // Less than 2 chars.
    }

    private <T> boolean hasViolations(T t) {
        Set<ConstraintViolation<T>> violations = this.validator.validate(t);
        return !violations.isEmpty();
    }
}
