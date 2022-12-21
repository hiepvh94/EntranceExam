package loan.management.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PaymentMode")
@Entity
@SuperBuilder
public class PaymentMode extends AuditedEntity{

    @Type(type = "text")
    @Column(name = "Name", nullable = false)
    private String name;

}
