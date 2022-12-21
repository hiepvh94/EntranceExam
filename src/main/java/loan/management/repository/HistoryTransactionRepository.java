package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.HistoryTransaction;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryTransactionRepository extends JpaRepository<HistoryTransaction, UUID> {

    List<HistoryTransaction> findByFacility_Applicant_Id(String id);


}
