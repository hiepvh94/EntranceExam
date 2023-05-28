package service.user;

import model.dto.response.user.UserResponses;
import model.entity.user.User;
import model.filter.UserFilter;

import java.util.List;

public interface UserService {

//    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
//
    User save(User user);

    List<UserResponses> getUsers(UserFilter userFilter);

    UserResponses getByEmailOrUsername(String search);
}
