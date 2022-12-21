package loan.management.service.user;

import loan.management.exception.NotFoundException;
import loan.management.model.entity.user.User;
import loan.management.repository.UserRepository;
import loan.management.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND_USERNAME_EMAIL = "User not found with username or email : ";
    private static final String USER_NOT_FOUND_ID = "User not found with Id : ";

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_USERNAME_EMAIL + usernameOrEmail));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ID + id));
        return UserPrincipal.create(user);
    }
}
