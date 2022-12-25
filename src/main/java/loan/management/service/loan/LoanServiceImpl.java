package loan.management.service.loan;


import loan.management.constant.ErrorConstants;
import loan.management.constant.LoanConstants;
import loan.management.model.dto.payment.FullyPayment;
import loan.management.model.dto.payment.PaymentContext;
import loan.management.model.dto.request.loan.LoanRequest;
import loan.management.model.dto.request.payment.PaymentRequest;
import loan.management.model.entity.*;
import loan.management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import loan.management.model.dto.payment.PartiallyPayment;
import loan.management.model.dto.response.payment.PaymentHistory;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanTypeRepository loanTypeRepository;
    private final HistoryTransactionRepository historyTransactionRepository;
    private final FacilityRepository facilityRepository;
    private final LoanManagementRepository loanManagementRepository;
    private final PaymentModeRepository paymentModeRepository;

    @Transactional
    @Override
    public void lendingLoanForFacility(LoanRequest loanRequest) {
        BigDecimal predictAmount = BigDecimal.ZERO;

        Facility facility = facilityRepository.findByNumber(loanRequest.getFacilityCode()).orElseThrow(
                () -> new EntityNotFoundException(ErrorConstants.FACILITY_NOT_FOUND)
        );

        LoanType loanType = loanTypeRepository.findById(loanRequest.getLoanType()).orElseThrow(
                () -> new EntityNotFoundException(ErrorConstants.LOAN_TYPE_NOT_FOUND)
        );

        List<LoanManagement> management = loanManagementRepository.findByFacility_IdAndLoanType_Id(facility.getId(), loanType.getId());

        if (management.size() > 0) {
            predictAmount = management.get(0).getCurrentAmount().add(loanRequest.getAmount());
            if (predictAmount.compareTo(loanType.getLimit()) > 0) {
                throw new IllegalArgumentException(ErrorConstants.AMOUNT_NOT_AVAILABLE);
            }
        } else {
            predictAmount = predictAmount.add(loanRequest.getAmount());
            if (predictAmount.compareTo(loanType.getLimit()) > 0) {
                throw new IllegalArgumentException(ErrorConstants.AMOUNT_NOT_AVAILABLE);
            }
        }

        List<LoanManagement> managements = loanManagementRepository.findByFacility_Id(facility.getId());
        BigDecimal realTotalAmount = BigDecimal.ZERO;
        for (LoanManagement loan : managements) {
            realTotalAmount.add(loan.getCurrentAmount());
        }
        if (realTotalAmount.compareTo(facility.getTotalLimit()) > 0) {
            throw new IllegalArgumentException(ErrorConstants.AMOUNT_NOT_AVAILABLE);
        }

        HistoryTransaction history = HistoryTransaction.builder()
                .facility(facility)
                .action(LoanConstants.LENDING)
                .loanType(loanType)
                .amount(loanRequest.getAmount())
                .build();
        historyTransactionRepository.save(history);

        PaymentMode paymentMode = paymentModeRepository.findById(loanRequest.getPaymentMode()).orElseThrow(
                () -> new NotFoundException(ErrorConstants.PAYMENT_MODE_NOT_FOUND)
        );


        if (management.size() > 0){
            BigDecimal currentAmount = management.get(0).getCurrentAmount().add(loanRequest.getAmount());
            management.get(0).setCurrentAmount(currentAmount);
        } else {
           LoanManagement newManagement = LoanManagement.builder()
                    .facility(facility)
                    .loanType(loanType)
                    .paymentMode(paymentMode)
                    .startDate(LocalDate.now())
                   .totalLimit(BigDecimal.ZERO)
                   .status("INPROGRESS")
                    .endDate(LocalDate.now().plusMonths(LoanConstants.EXPIRED_LOAN_TIME))
                    .currentAmount(loanRequest.getAmount())
                    .build();
//           management.clear();
           management.add(newManagement);
        }
        loanManagementRepository.save(management.get(0));

    }

    @Transactional
    @Override
    public void paymentForFacility(PaymentRequest paymentRequest) {
        Facility facility = facilityRepository.findByNumber(paymentRequest.getFacilityCode()).orElseThrow(
                () -> new EntityNotFoundException(ErrorConstants.FACILITY_NOT_FOUND)
        );

        LoanType loanType = loanTypeRepository.findById(paymentRequest.getLoanType()).orElseThrow(
                () -> new EntityNotFoundException(ErrorConstants.LOAN_TYPE_NOT_FOUND)
        );

        HistoryTransaction history = HistoryTransaction.builder()
                .facility(facility)
                .action(LoanConstants.PAYMENT)
                .loanType(loanType)
                .amount(paymentRequest.getAmount())
                .build();
        historyTransactionRepository.save(history);

        List<LoanManagement> management = loanManagementRepository.findByFacility_IdAndLoanType_Id(facility.getId(), loanType.getId());
        if (management.size() < 0) {
            throw new EntityNotFoundException(ErrorConstants.LOAN_MANAGEMENT_NOT_FOUND);
        }


        BigDecimal newAmount;
        if ("Fully".equalsIgnoreCase(paymentRequest.getPaymentType())){
            newAmount= PaymentContext.builder().paymentMethodDTO(FullyPayment.builder().build()).build().payment(management.get(0).getLoanType().getInterestRate(), management.get(0).getCurrentAmount(), paymentRequest.getAmount());
        } else {
            newAmount= PaymentContext.builder().paymentMethodDTO(PartiallyPayment.builder().build()).build().payment(management.get(0).getLoanType().getInterestRate(), management.get(0).getCurrentAmount(), paymentRequest.getAmount());
        }

        if (management.get(0).getCurrentAmount() == BigDecimal.ZERO){
            management.get(0).setStatus("Completed");
        }

        management.get(0).setCurrentAmount(newAmount);
        loanManagementRepository.save(management.get(0));

    }

    @Override
    public List<LoanManagement> getLoanTotal(String applicantId) {
        List<LoanManagement> loanManagements = loanManagementRepository.findByFacility_Applicant_Id(applicantId);
        return loanManagements;
    }

    @Override
    public List<PaymentHistory> getLoanHistory(String applicantId) {
        List<HistoryTransaction> historyTransactions = historyTransactionRepository.findByFacility_Applicant_Id(applicantId);
        return PaymentHistory.entitiesToDTOs(historyTransactions);
    }
}
