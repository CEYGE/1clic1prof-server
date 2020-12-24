package fr.clic1prof.serverapp.model.profile.model;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

public class StudentProfile extends UserProfile {

    private SchoolLevel level;

    public StudentProfile(String firstName, String lastName, String pictureUrl, SchoolLevel level) {
        super(firstName, lastName, pictureUrl);
        this.level = level;
    }

    public SchoolLevel getLevel() {
        return this.level;
    }
}
