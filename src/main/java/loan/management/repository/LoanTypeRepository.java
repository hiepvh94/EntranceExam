package loan.management.repository;

import loan.management.model.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {

}
