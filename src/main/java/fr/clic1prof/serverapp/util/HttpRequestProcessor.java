package fr.clic1prof.serverapp.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.function.Supplier;

public class HttpRequestProcessor {

    public static ResponseEntity<?> update(BindingResult result, Supplier<Boolean> supplier) {

        // Request is understood but there are data errors.
        if(result.hasErrors()) return ResponseEntity.unprocessableEntity().build();

        boolean updated = supplier.get();

        // If updated is true, the request is successful request but there is no data to return.
        // Else, an error occurred with the database.
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.unprocessableEntity().build();
    }
}
