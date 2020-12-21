package fr.clic1prof.serverapp.model.profile.model;

public class UserProfile implements Profile {

    private String firstName, lastName;

    public UserProfile(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
}
