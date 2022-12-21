package loan.management.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LoanType")
@Entity
@SuperBuilder
public class LoanType extends AuditedEntity{

    @Type(type = "text")
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "InterestRate", nullable = false)
    private BigDecimal interestRate;

    @Type(type = "text")
    @Column(name = "Currency", nullable = false)
    private String currency;

    @Column(name = "loanLimit", nullable = false)
    private BigDecimal limit;

}
