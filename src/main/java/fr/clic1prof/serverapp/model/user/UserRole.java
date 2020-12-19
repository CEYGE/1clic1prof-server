package fr.clic1prof.serverapp.model.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    TEACHER(Names.TEACHER), STUDENT(Names.STUDENT);

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Optional<UserRole> getByEnumName(String name) {

        String nameInUpperCase = name.toUpperCase();

        return Arrays.stream(UserRole.values())
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
