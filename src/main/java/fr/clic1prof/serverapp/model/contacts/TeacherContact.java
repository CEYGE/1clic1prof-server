package fr.clic1prof.serverapp.model.contacts;

import java.util.Objects;

public class TeacherContact extends Contact {

    private String studyLevel;

    public TeacherContact(String firstName, String lastName, String studyLevel) {
        super(firstName, lastName);
        this.studyLevel = studyLevel;
    }

    public String getStudyLevel() {
        return this.studyLevel;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof TeacherContact)) return false;

        if (!super.equals(object)) return false;

        TeacherContact that = (TeacherContact) object;

        return Objects.equals(getStudyLevel(), that.getStudyLevel());
    }
}
