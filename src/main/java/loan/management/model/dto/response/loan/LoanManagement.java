package loan.management.model.dto.response.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanManagement {

    private String facility;
    private String loanType;
    private BigDecimal currentAmount;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    private static LoanManagement entityDTO(loan.management.model.entity.LoanManagement loanManagement){
        return LoanManagement.builder()
                .facility(loanManagement.getFacility().getNumber())
                .loanType(loanManagement.getLoanType().getName())
                .currentAmount(loanManagement.getCurrentAmount())
                .status(loanManagement.getStatus())
                .startDate(loanManagement.getStartDate())
                .endDate(loanManagement.getEndDate())
                .build();
    }

    public static List<LoanManagement> entitiesDTO(List<loan.management.model.entity.LoanManagement> loanManagements){
        return loanManagements.stream().map(item -> LoanManagement.entityDTO(item))
                .collect(Collectors.toList());
    }
}
