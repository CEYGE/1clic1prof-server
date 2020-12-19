package fr.clic1prof.serverapp.dao.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserProfileDAO")
public class UserProfileDAO implements IUserProfileDAO {

    @Autowired
    protected JdbcTemplate template;

    @Override
    public boolean updatePassword(int id, String password) {

        String query = "UPDATE auth_user SET user_pass = ? WHERE id = ?;";

        return this.template.update(query, password, id) > 0;
    }

    @Override
    public boolean updateFirstName(int id, String firstName) {

        String query = "UPDATE utilisateur SET user_prenom = ? WHERE user_id = ?;";

        return this.template.update(query, firstName, id) > 0;
    }

    @Override
    public boolean updateLastName(int id, String lastName) {

        String query = "UPDATE utilisateur SET user_nom = ? WHERE user_id = ?;";

        return this.template.update(query, lastName, id) > 0;
    }

    @Override
    public boolean updateNotificationState(int id, boolean flag) {
        return false;
    }

    @Override
    public String getPassword(int id) {

        String query = "SELECT user_pass FROM auth_user WHERE id = ?;";

        return this.template.queryForObject(query, String.class, id);
    }
}
