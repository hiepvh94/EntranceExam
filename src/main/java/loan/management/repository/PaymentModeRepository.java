package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.PaymentMode;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, String> {
}
