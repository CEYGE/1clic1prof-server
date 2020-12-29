package fr.clic1prof.serverapp.file.model;

import java.util.Arrays;
import java.util.Optional;

public enum DocumentType {

    PROFILE_PICTURE, COURSE_DOCUMENT, PAYSLIP, INVOICE, CV, UNKNOWN;

    public static Optional<DocumentType> getTypeByName(String name) {

        final String nameInUppercase = name.toUpperCase();

        return Arrays.stream(values())
                .filter(type -> type.name().equals(nameInUppercase))
                .findFirst();
    }
}
