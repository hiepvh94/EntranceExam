package loan.management.model.dto.response.payment;

import loan.management.model.entity.HistoryTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO {

    private String action;
    private String loanType;
    private BigDecimal amount;

    private static PaymentDTO entitiesToDTO(HistoryTransaction historyTransaction){
        return PaymentDTO.builder()
                .action(historyTransaction.getAction())
                .loanType(historyTransaction.getLoanType().getName())
                .amount(historyTransaction.getAmount())
                .build();
    }

    public static List<PaymentDTO> entitiesToDTOs(List<HistoryTransaction> historyTransactions){
        return historyTransactions.stream().map(item -> PaymentDTO.entitiesToDTO(item)).collect(Collectors.toList());
    }
}
