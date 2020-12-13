package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository("UserProfileDAOImpl")
public class UserProfileDAOImpl implements UserProfileDAO {

    @Autowired
    protected JdbcTemplate template;

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
    public boolean updatePicture(Email email, Path picture) {
        return false;
    }

    @Override
    public boolean updateNotificationState(Email email, boolean flag) {
        return false;
    }
}
