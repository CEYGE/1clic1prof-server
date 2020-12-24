package fr.clic1prof.serverapp.model.contacts;

public class Contact implements ContactModel {

    private String firstName, lastName;

    public Contact(String firstName, String lastName) {
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

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof Contact)) return false;

        Contact contact = (Contact) object;

        return getFirstName().equals(contact.getFirstName()) && getLastName().equals(contact.getLastName());
    }
}
