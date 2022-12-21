package loan.management.model.dto.request.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanRequest {

    private String facilityCode;
    private String loanType;
    private String paymentMode;
    private String action;
    private BigDecimal amount;
//    private LocalDate startDate;
//    private LocalDate endDate;

}
