package loan.management.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Applicant")
@Entity
@SuperBuilder
public class Applicant extends AuditedEntity{

    @Type(type = "text")
    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Type(type = "text")
    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Type(type = "text")
    @Column(name = "IdentityCode", nullable = false)
    private String identityCode;

    @OneToMany(mappedBy = "applicant")
    private List<Facility> facilities;

}
