package fr.clic1prof.serverapp.model.profile;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof SchoolLevel)) return false;

        SchoolLevel level = (SchoolLevel) object;

        return getId() == level.getId() && Objects.equals(getLabel(), level.getLabel());
    }
}
