package loan.management.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import loan.management.model.dto.response.loan.LoanManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import loan.management.controller.v1.APIV1Controller;
import loan.management.model.dto.request.loan.LoanRequest;
import loan.management.model.dto.request.payment.PaymentRequest;
import loan.management.model.dto.response.payment.PaymentHistory;
import loan.management.service.loan.LoanService;

import java.util.List;

@APIV1Controller
@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/loan")
    public ResponseEntity lendingLoan(@org.springframework.web.bind.annotation.RequestBody LoanRequest loanRequest) throws Throwable {
        loanService.lendingLoanForFacility(loanRequest);
        return null;
    }

    @PostMapping("/payment")
    public RequestBody payment(@org.springframework.web.bind.annotation.RequestBody PaymentRequest paymentRequest){
        loanService.paymentForFacility(paymentRequest);
        return null;
    }

    @PostMapping("/applicant/{id}/loans-management")
    public ResponseEntity getTotalForApplicant(@PathVariable(name = "id") String applicantId){
        List<loan.management.model.entity.LoanManagement> loanManagements = loanService.getLoanTotal(applicantId);
        return ResponseEntity.ok().body(LoanManagement.entitiesDTO(loanManagements));
    }

    @PostMapping("/applicant/{id}/history")
    public ResponseEntity getHisotryForApplicant(@PathVariable(name = "id") String applicantId){
        List<PaymentHistory> paymentDTOS = loanService.getLoanHistory(applicantId);
        return ResponseEntity.ok().body(paymentDTOS);
    }
}
