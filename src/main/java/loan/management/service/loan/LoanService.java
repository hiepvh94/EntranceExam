package loan.management.service.loan;

import loan.management.model.dto.request.loan.LoanRequest;
import loan.management.model.dto.request.payment.PaymentRequest;
import loan.management.model.entity.LoanManagement;
import loan.management.model.dto.response.payment.PaymentHistory;

import java.util.List;

public interface LoanService {

    void lendingLoanForFacility(LoanRequest loanRequest) throws Throwable;

    void paymentForFacility(PaymentRequest paymentRequest);

    List<LoanManagement> getLoanTotal(String applicantId);

    List<PaymentHistory> getLoanHistory(String applicantId);
}
