package fr.clic1prof.serverapp.model.profile.model;

import fr.clic1prof.serverapp.model.profile.Speciality;

import java.util.Collections;
import java.util.List;

public class TeacherProfile extends UserProfile {

    private String description, studies;
    private List<Speciality> specialities;

    public TeacherProfile(int id, String firstName, String lastName, String pictureUrl, String description, String studies, List<Speciality> specialities) {
        super(id, firstName, lastName, pictureUrl);
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
