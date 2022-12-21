package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.Applicant;

import java.util.UUID;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, UUID> {

    Applicant findByIdentityCode(String identityCode);
}
