package fr.clic1prof.serverapp.model.profile;

public class SchoolLevel {

    private int id;
    private String label;

    public SchoolLevel(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }
}
