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
@Table(name = "Facility")
@Entity
@SuperBuilder
public class Facility extends  AuditedEntity{

    @Column(name = "NumberCard", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "ApplicantId")
    private Applicant applicant;

//    @Column(name = "ApplicantId", nullable = false)
//    private UUID applicantId;

    @Column(name = "TotalLimit", nullable = false)
    private BigDecimal totalLimit;

    @Column(name = "Currency", nullable = false)
    private String currency;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;
}
