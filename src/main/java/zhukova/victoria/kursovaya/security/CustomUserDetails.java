package zhukova.victoria.kursovaya.security;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zhukova.victoria.kursovaya.model.dto.DoctorDto;
import zhukova.victoria.kursovaya.model.dto.SecurityUser;
import zhukova.victoria.kursovaya.model.dto.UserDto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static CustomUserDetails getUserDetails(SecurityUser user) {
        return new CustomUserDetails(user.getEmail(), user.getPassword(),
                mapToGrantedAuthorities(user));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(SecurityUser user) {
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");
        return Objects.equals(user.getClass(), DoctorDto.class) ?
                List.of(userRole,adminRole) :
                List.of(userRole);
    }
}
