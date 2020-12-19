package fr.clic1prof.serverapp.model.user.attributes;

import java.util.Arrays;
import java.util.Optional;

public enum Role {

    TEACHER(Names.TEACHER), STUDENT(Names.STUDENT);

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Optional<Role> getByEnumName(String name) {

        String nameInUpperCase = name.toUpperCase();

        return Arrays.stream(Role.values())
                .filter(role -> role.name().equals(nameInUpperCase))
                .findFirst();
    }

    // This class has for goal to allow to use role constants
    // in annotations like RolesAllowed for example.
    public static class Names {

        public static final String TEACHER = "ROLE_TEACHER";
        public static final String STUDENT = "ROLE_STUDENT";
    }
}
