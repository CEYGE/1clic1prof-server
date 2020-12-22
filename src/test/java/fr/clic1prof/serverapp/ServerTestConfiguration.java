package fr.clic1prof.serverapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@TestConfiguration
// Custom properties file with test database.
@TestPropertySource(locations = "classpath:./application-test.properties")
// Each SQL transaction will be rollback.
@Rollback
@Transactional
public class ServerTestConfiguration {

}
