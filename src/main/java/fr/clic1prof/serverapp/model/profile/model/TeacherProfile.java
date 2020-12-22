package fr.clic1prof.serverapp.model.profile.model;

import fr.clic1prof.serverapp.model.profile.Speciality;

import java.util.Collections;
import java.util.List;

public class TeacherProfile extends UserProfile {

    private String description, studies;
    private List<Speciality> specialities;

    public TeacherProfile(String firstName, String lastName, String description, String studies, List<Speciality> specialities) {
        super(firstName, lastName);
        this.description = description;
        this.studies = studies;
        this.specialities = specialities;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStudies() {
        return this.studies;
    }

    public List<Speciality> getSpecialities() {
        return Collections.unmodifiableList(this.specialities);
    }
}