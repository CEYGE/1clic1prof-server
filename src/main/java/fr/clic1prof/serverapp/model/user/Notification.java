package fr.clic1prof.serverapp.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Notification {
    @NotNull
    private boolean flag;

    public Notification(boolean flag) {
        this.flag = flag;
    }

    public boolean getValue() {
        return this.flag;
    }
}
