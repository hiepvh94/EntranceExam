package loan.management.service.role;

import loan.management.model.entity.user.Role;
import loan.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        if(Objects.nonNull(role)) {
            return role;
        }
        return null;
    }
}
