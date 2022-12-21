package loan.management.security;

import loan.management.model.entity.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import loan.management.constant.RoleConstants;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(RoleConstants.ROLE_PREFIX +role.getName()))
                .collect(Collectors.toSet());

        return UserPrincipal.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(authorities)
                .build();
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
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
