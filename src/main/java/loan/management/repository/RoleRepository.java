package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
