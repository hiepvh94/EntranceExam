package loan.management.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HistoryTransaction")
@Entity
@SuperBuilder
public class HistoryTransaction extends AuditedEntity{

    @Column(name = "Action", nullable = false)
    private String action;

    @ManyToOne
    @JoinColumn(name = "FacilityId")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "LoanTypeId")
    private  LoanType loanType;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

}
