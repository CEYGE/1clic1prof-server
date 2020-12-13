package fr.clic1prof.serverapp.model.registration;

import java.util.Arrays;
import java.util.Optional;

public enum RegistrationType {

    STUDENT;

    public static Optional<RegistrationType> getByName(String name) {
        return Arrays.stream(RegistrationType.values())
                .filter(type -> type.name().equals(name.toUpperCase()))
                .findFirst();
    }
}
