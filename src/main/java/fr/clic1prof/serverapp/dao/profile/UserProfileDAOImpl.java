package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("UserProfileDAOImpl")
public class UserProfileDAOImpl implements UserProfileDAO {

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
    public String getPassword(int id) {

        String query = "SELECT password FROM auth_user WHERE id = ?;";

        return this.template.queryForObject(query, String.class, id);
    }

    @Override
    public Profile getProfile(int id) {

        String query = "SELECT user_first_name, user_last_name, doc_id FROM user " +
                "LEFT OUTER JOIN document ON doc_owner_id = user_id AND doc_type = ?" +
                "WHERE user_id = ?;";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            int pictureId = result.getInt("doc_id");

            return new UserProfile(id, firstName, lastName, pictureId);
        };
        return this.template.queryForObject(query, mapper, DocumentType.PROFILE_PICTURE.name(), id);
    }
}
