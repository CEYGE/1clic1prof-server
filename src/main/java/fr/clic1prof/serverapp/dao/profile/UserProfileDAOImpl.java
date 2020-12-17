package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository("UserProfileDAOImpl")
public class UserProfileDAOImpl implements UserProfileDAO {

    @Autowired
    protected JdbcTemplate template;

    @Override
    public boolean updatePassword(int id, Password password) {

        String query = "UPDATE auth_user SET user_pass = ? WHERE user_id = ?;";

        return this.template.update(query, password.getHashed(), id) > 0;
    }

    @Override
    public boolean updateFirstName(int id, Name name) {

        String query = "UPDATE utilisateur SET user_prenom = ? WHERE user_id = ?;";

        return this.template.update(query, name.getValue(), id) > 0;
    }

    @Override
    public boolean updateLastName(int id, Name name) {

        String query = "UPDATE utilisateur SET user_nom = ? WHERE user_id = ?;";

        return this.template.update(query, name.getValue(), id) > 0;
    }

    @Override
    public boolean updatePicture(int id, Path picture) {
        return false;
    }

    @Override
    public boolean updateNotificationState(int id, boolean flag) {
        return false;
    }
}
