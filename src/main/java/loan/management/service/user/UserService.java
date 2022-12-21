package loan.management.service.user;

import loan.management.model.entity.user.User;
import loan.management.model.dto.response.user.UserResponses;
import loan.management.model.filter.UserFilter;

import java.util.List;

public interface UserService {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);

    List<UserResponses> getUsers(UserFilter userFilter);

    UserResponses getByEmailOrUsername(String search);
}
