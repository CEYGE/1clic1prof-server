package fr.clic1prof.serverapp.model.contacts;

public class TeacherContact extends Contact {

    private String studyLevel;

    public TeacherContact(String firstName, String lastName, String studyLevel) {
        super(firstName, lastName);
        this.studyLevel = studyLevel;
    }

    public String getStudyLevel() {
        return this.studyLevel;
    }
}
