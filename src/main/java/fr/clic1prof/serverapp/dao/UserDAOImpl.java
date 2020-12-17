package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.model.user.SimpleUser;
import fr.clic1prof.serverapp.model.user.User;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.attributes.Email;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("UserDAOImpl") // Let Spring know that it exists.
public class UserDAOImpl implements UserDAO {

    @Autowired
    protected JdbcTemplate template;

    @Override
    public Optional<User> findByUsername(String username) {

        String query = "SELECT " +
                "auth.id, auth.user_email, auth.user_pass, user_role " +
                "FROM utilisateur " +
                "JOIN (SELECT id, user_email, user_pass from auth_user AS auth WHERE user_email = ?) AS auth " +
                "ON utilisateur.user_id = auth.id;";

        RowMapper<SimpleUser> mapper = this.getSimpleUserMapper();

        // Using a query instead of queryForObject because there is the risk that
        // the user isn't in the database so the query will not return a single row.
        // This will throw an error and to prevent the fact of doing two requests,
        // this solution has been chosen for better performances.
        List<SimpleUser> result = this.template.query(query, mapper, username);

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    @Transactional // If a request fails, restoring the previous state.
    public boolean register(Registration registration) {

        String query1 = "INSERT INTO auth_user (user_pass, user_email) VALUES (?,?);";

        Email email = registration.getEmail();
        Password password = registration.getPassword();

        int rows = this.template.update(query1, password.getHashed(), email.getValue());

        if(rows == 0) return false; // If no row has been affected, return false.

        String query2 = "UPDATE utilisateur SET user_nom = ?, user_prenom = ?, user_role = ?;";

        rows = this.template.update(query2,
                registration.getFirstName().getValue(),
                registration.getLastName().getValue(),
                registration.getType().name());

        return rows > 0; // If one row has been affected, return true.
    }

    private RowMapper<SimpleUser> getSimpleUserMapper() {
        return (rs, i) -> {

            int id = rs.getInt("id");
            String username = rs.getString("user_email");
            String password = rs.getString("user_pass");
            String role = rs.getString("user_role");

            Role userRole = Role.valueOf(role);

            return new SimpleUser(id, username, password, Collections.singletonList(userRole));
        };
    }
}
