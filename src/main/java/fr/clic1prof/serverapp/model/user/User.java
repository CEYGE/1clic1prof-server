package fr.clic1prof.serverapp.model.user;

import fr.clic1prof.serverapp.model.user.attributes.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class User implements UserModel {

    private int id;
    private String username;
    private String password;
    private boolean locked, enabled;
    private Collection<Role> roles;

    // Used by Builder.
    private User() {}

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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
    public Collection<Role> getRoles() {
        return this.roles;
    }

    public static class Builder {

        private final int id;
        private final String username, password;

        private boolean locked, enabled;
        private Collection<Role> roles;

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
            this.roles = new ArrayList<>();
            this.locked = false;
            this.enabled = true;
        }

        public Builder roles(Collection<Role> roles) {
            this.roles = roles;
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
            user.roles = this.roles;

            return user;
        }
    }
}
