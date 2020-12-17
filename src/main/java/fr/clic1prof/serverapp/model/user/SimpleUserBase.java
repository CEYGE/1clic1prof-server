package fr.clic1prof.serverapp.model.user;

public class SimpleUserBase implements UserBase {

    private final int id;

    public SimpleUserBase(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
