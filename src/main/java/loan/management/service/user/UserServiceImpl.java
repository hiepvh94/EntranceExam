package loan.management.service.user;

import loan.management.constant.EmailConstants;
import loan.management.model.entity.user.User;
import loan.management.repository.UserRepository;
import loan.management.utils.ThreadUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import loan.management.model.dto.response.user.UserResponses;
import loan.management.model.filter.UserFilter;
import loan.management.service.email.IEmailService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final IEmailService emailService;
    private final EntityManager entityManager;
    @Override
    public Boolean existsByUsername(String username) {
        try {
            Boolean user = userRepository.existsByUserName(username);
            return Objects.nonNull(user) ? user : false;
        }catch (Exception exception){
            return false;
        }

    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            Boolean user = userRepository.existsByEmail(email);
            return Objects.nonNull(user) ? user : false;
        }catch (Exception exception){
            return false;
        }
    }


    @Override
    @Transactional
    public User save(User user) {
        ThreadUtil.runAsync(() -> sendEmailSignUpUser(user));

        return userRepository.save(user);
    }

    private void sendEmailSignUpUser(User user){
        String subject = "Sign Up User";
        Map<String, Object> emailContent = new HashMap<>();

        emailContent.put(EmailConstants.EMAIL, user.getEmail());
        emailContent.put(EmailConstants.FULLNAME, user.getFullName());

        emailService.sendEmail(new String[]{user.getEmail()}
                , new String[0]
                , subject
                , EmailConstants.SIGN_UP_USER_TEMPLATE_MAIL
                , emailContent);
    }

    @Override
    public List<UserResponses> getUsers(UserFilter userFilter) {

        return null;
    }

    @Override
    public UserResponses getByEmailOrUsername(String search) {
        if(! StringUtils.hasText(search)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);

        Predicate conditionEmail = criteriaBuilder.equal(userRoot.get("email"), search);
        Predicate conditionUserName = criteriaBuilder.equal(userRoot.get("userName"), search);
        Predicate predicate = criteriaBuilder.or(conditionEmail, conditionUserName);

        query.where(predicate);

        List<User> users = entityManager.createQuery(query).getResultList();

        if(CollectionUtils.isNotEmpty(users)){
            return UserResponses.fromUser(users.get(0));
        }

        return null;
    }
}
