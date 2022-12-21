package loan.management.model.dto.response.payment;//package com.example.model.dto.response;

import loan.management.model.entity.HistoryTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentHistory {

    private String id;
    private String action;
    private Date time;
    private String facility;
    private String loanType;
    private BigDecimal amount;

    public static PaymentHistory entitiesToDTO(HistoryTransaction historyTransaction){
        return PaymentHistory.builder()
                .id(historyTransaction.getId())
                .action(historyTransaction.getAction())
                .time(historyTransaction.getCreationTime())
                .facility(historyTransaction.getFacility().getNumber())
                .loanType(historyTransaction.getLoanType().getName())
                .amount(historyTransaction.getAmount())
                .build();
    }
    public static List<PaymentHistory> entitiesToDTOs(List<HistoryTransaction> historyTransactions){
        return historyTransactions.stream().map(item -> PaymentHistory.entitiesToDTO(item)).collect(Collectors.toList());
    }
}
