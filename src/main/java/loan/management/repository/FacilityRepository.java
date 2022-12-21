package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.Facility;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {


    Optional<Facility> findByNumber(String facilityCode);
}
