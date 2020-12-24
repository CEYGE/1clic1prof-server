package fr.clic1prof.serverapp.model.profile.model;

public class UserProfile implements Profile {

    private String firstName, lastName;
    private String pictureUrl;

    public UserProfile(String firstName, String lastName, String pictureUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPictureUrl() {
        return this.pictureUrl;
    }
}
