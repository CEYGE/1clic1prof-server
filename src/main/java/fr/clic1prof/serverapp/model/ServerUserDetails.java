package fr.clic1prof.serverapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class ServerUserDetails implements UserDetails {

    private int id;
    private String username;
    private String password;
    private boolean locked, enabled;
    private Collection<? extends GrantedAuthority> collection;

    private ServerUserDetails() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.collection;
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

    public int getId() {
        return this.id;
    }

    public static class Builder {

        private final int id;
        private final String username, password;

        private boolean locked, enabled;
        private Collection<? extends GrantedAuthority> authorities;

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
            this.authorities = new ArrayList<>();
            this.locked = false;
            this.enabled = true;
        }

        public Builder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
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

        public ServerUserDetails build() {

            ServerUserDetails details = new ServerUserDetails();

            details.id = this.id;
            details.username = this.username;
            details.password = this.password;
            details.locked = this.locked;
            details.enabled = this.enabled;
            details.collection = this.authorities;

            return details;
        }
    }
}
