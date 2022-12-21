package loan.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import loan.management.model.entity.LoanManagement;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanManagementRepository extends JpaRepository<LoanManagement, UUID> {

    List<LoanManagement> findByFacility_Applicant_Id(String id);

    List<LoanManagement> findByFacility_IdAndLoanType_Id(String id, String id1);
    

//   List<LoanManagement> findAllByFacilityIdAndLoanTypeId(String fromString, String loanType);

//    List<LoanManagement> findAllByFacility(String id);

    List<LoanManagement> findByFacility_Id(String id);


}
