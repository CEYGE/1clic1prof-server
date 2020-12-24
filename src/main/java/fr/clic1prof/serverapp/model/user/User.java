package fr.clic1prof.serverapp.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class User implements UserModel {

    private int id;
    private String username;
    private String password;
    private boolean locked, enabled;
    private UserRole role;

    // Used by Builder.
    private User() {}

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role.getName()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public UserRole getRole() {
        return this.role;
    }

    public static class Builder {

        private final int id;
        private final String username, password;

        private boolean locked, enabled;
        private UserRole role;

        public Builder(int id, String username, String password) {

            if(id < 0)
                throw new IllegalArgumentException("Id cannot be negative.");

            if(username == null)
                throw new IllegalArgumentException("Username cannot be null.");

            if(password == null)
                throw new IllegalArgumentException("Password cannot be null.");

            this.id = id;
            this.username = username;
            this.password = password;
            this.locked = false;
            this.enabled = true;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder locked(boolean locked) {
            this.locked = locked;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {

            User user = new User();

            user.id = this.id;
            user.username = this.username;
            user.password = this.password;
            user.locked = this.locked;
            user.enabled = this.enabled;
            user.role = this.role;

            return user;
        }
    }
}
