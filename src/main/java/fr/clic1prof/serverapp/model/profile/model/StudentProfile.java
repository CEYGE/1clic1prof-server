package fr.clic1prof.serverapp.model.profile.model;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

public class StudentProfile extends UserProfile {

    private SchoolLevel level;

    public StudentProfile(int id, String firstName, String lastName, int pictureId, SchoolLevel level) {
        super(id, firstName, lastName, pictureId);
        this.level = level;
    }

    public SchoolLevel getLevel() {
        return this.level;
    }
}
