package loan.management.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LoanManagement")
@Entity
@SuperBuilder
public class LoanManagement extends AuditedEntity{

    @ManyToOne
    @JoinColumn(name = "FacilityId")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "LoanTypeId")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "PaymentModeId")
    private PaymentMode paymentMode;

    @Column(name = "TotalLimit", nullable = false)
    private BigDecimal totalLimit;

    @Column(name = "CurrentAmount", nullable = false)
    private BigDecimal currentAmount;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

}
