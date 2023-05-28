package service.user;

import lombok.RequiredArgsConstructor;
import model.entity.user.User;
import repository.UserRepository;
import security.UserPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND_EMAIL = "User not found with email : ";
    private static final String USER_NOT_FOUND_ID = "User not found with Id : ";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_EMAIL + email));
        return UserPrincipal.create(user);
//    	return null;
    }

    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ID + id));
        return UserPrincipal.create(user);
    }
}
