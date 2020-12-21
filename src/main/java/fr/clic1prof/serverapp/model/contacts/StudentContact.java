package fr.clic1prof.serverapp.model.contacts;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

public class StudentContact extends Contact {

    private SchoolLevel schoolLevel;

    public StudentContact(String firstName, String lastName, SchoolLevel level) {
        super(firstName, lastName);
        this.schoolLevel = level;
    }

    public SchoolLevel getSchoolLevel() {
        return this.schoolLevel;
    }
}
