package fr.clic1prof.serverapp.model.profile.model;

public class UserProfile implements Profile {

    private int id;
    private String firstName, lastName;
    private int pictureId;

    public UserProfile(int id, String firstName, String lastName, int pictureId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureId = pictureId;
    }

    @Override
    public int getId() {
        return this.id;
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
    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
