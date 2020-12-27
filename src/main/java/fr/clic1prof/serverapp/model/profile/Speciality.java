package fr.clic1prof.serverapp.model.profile;

import java.util.Objects;

public class Speciality {

    private int id;
    private String label;

    public Speciality(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof Speciality)) return false;

        Speciality that = (Speciality) object;

        return getId() == that.getId() && Objects.equals(getLabel(), that.getLabel());
    }
}
