package fr.clic1prof.serverapp.dao.impl;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.SimpleUser;
import fr.clic1prof.serverapp.model.Role;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("UserDAOImpl") // Let Spring know that it exists.
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Optional<SimpleUser> findByUsername(String username) {

        String query = "select auth.user_email, auth.user_pass, user_role from utilisateur " +
                "join (select id, user_email, user_pass from auth_user as auth where user_email = ?) as auth " +
                "on utilisateur.user_id = auth.id;";

        RowMapper<SimpleUser> mapper = this.getSimpleUserMapper();

        List<SimpleUser> result = this.template.query(query, mapper, username);

        if(result.size() != 1) return Optional.empty();

        return Optional.of(result.get(0));
    }

    @Override
    public boolean register(Registration registration) {

        String query1 = "INSERT INTO auth_user (user_pass, user_email) VALUES (?,?);";

        Email email = registration.getEmail();
        Password password = registration.getPassword();

        int rows = this.template.update(query1, password.getHashed(), email.getValue());

        if(rows != 1) return false; // If no row has been affected, return false.

        String query2 = "UPDATE utilisateur SET user_nom = ?, user_prenom = ?, user_role = ?;";

        rows = this.template.update(query2,
                registration.getFirstName().getValue(),
                registration.getLastName().getValue(),
                registration.getType().name());

        return rows == 1; // If one row has been affected, return true.
    }

    @Override
    public boolean updatePassword(Email email, Password password) {

        String query = "UPDATE auth_user SET user_pass = ? WHERE user_email = ?;";

        return this.template.update(query, password.getHashed(), email.getValue()) == 1;
    }

    @Override
    public boolean updateFirstName(Email email, Name name) {

        String query = "UPDATE utilisateur SET user_prenom = ? WHERE user_email = ?;";

        return this.template.update(query, name.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateLastName(Email email, Name name) {

        String query = "UPDATE utilisateur SET user_nom = ? WHERE user_email = ?;";

        return this.template.update(query, name.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateDescription(Email email, Description description) {

        String query = "UPDATE utilisateur SET user_descriptionProfil = ? WHERE user_email = ?;";

        return this.template.update(query, description.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateStudyLevel(Email email, StudyLevel studyLevel) {

        String query = "UPDATE utilisateur SET user_niveauEtude = ? WHERE user_email = ?;";

        return this.template.update(query, studyLevel.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateRole(Email email, Role role) {

        String query = "UPDATE utilisateur SET user_role = ? WHERE user_email = ?;";

        return this.template.update(query, role.name(), email.getValue()) == 1;
    }

    private RowMapper<SimpleUser> getSimpleUserMapper() {
        return (rs, i) -> {

            String username = rs.getString("user_email");
            String password = rs.getString("user_pass");
            String role = rs.getString("user_role");

            Role userRole = Role.valueOf(role);

            return new SimpleUser(username, password, userRole);
        };
    }
}
