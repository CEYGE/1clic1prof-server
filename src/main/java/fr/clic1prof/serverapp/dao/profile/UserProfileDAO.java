package fr.clic1prof.serverapp.dao.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository("UserProfileDAO")
public class UserProfileDAO implements IUserProfileDAO {

    @Autowired
    protected JdbcTemplate template;

    @Override
    public boolean updatePassword(int id, String password) {

        String query = "UPDATE auth_user SET password = ? WHERE id = ?;";

        return this.template.update(query, password, id) > 0;
    }

    @Override
    public boolean updateFirstName(int id, String firstName) {

        String query = "UPDATE user SET user_first_name = ? WHERE user_id = ?;";

        return this.template.update(query, firstName, id) > 0;
    }

    @Override
    public boolean updateLastName(int id, String lastName) {

        String query = "UPDATE user SET user_last_name = ? WHERE user_id = ?;";

        return this.template.update(query, lastName, id) > 0;
    }

    @Override
    public boolean updatePicture(int id, UUID uuid) {

        String query = "UPDATE user SET user_picture = ? WHERE user_id = ?;";

        return this.template.update(query, uuid.toString(), id) > 0;
    }

    @Override
    @Transactional
    public UUID deletePicture(int id) {

        String select = "SELECT user_picture FROM user WHERE user_id = ?;";

        String uuid = this.template.queryForObject(select, String.class, id);

        if(uuid == null) return null;

        String delete = "UPDATE user SET user_picture = null WHERE user_id = ?;";

        boolean deleted = this.template.update(delete, id) > 0;

        return deleted ? UUID.fromString(uuid) : null;
    }

    @Override
    public String getPassword(int id) {

        String query = "SELECT password FROM auth_user WHERE id = ?;";

        return this.template.queryForObject(query, String.class, id);
    }
}
