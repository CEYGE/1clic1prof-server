package fr.clic1prof.serverapp.model.contacts;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

import java.util.Objects;

public class StudentContact extends Contact {

    private SchoolLevel schoolLevel;

    public StudentContact(int id, String firstName, String lastName, SchoolLevel level) {
        super(id, firstName, lastName);
        this.schoolLevel = level;
    }

    public SchoolLevel getSchoolLevel() {
        return this.schoolLevel;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof StudentContact)) return false;

        if (!super.equals(object)) return false;

        StudentContact that = (StudentContact) object;

        return Objects.equals(getSchoolLevel(), that.getSchoolLevel());
    }
}
