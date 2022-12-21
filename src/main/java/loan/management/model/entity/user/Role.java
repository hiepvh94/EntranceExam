package loan.management.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import loan.management.model.entity.AuditedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "Role")
public class Role extends AuditedEntity {

    @Column(unique = true)
    @NotNull(message = "Please provider a role name !")
    private String name;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade =  {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "roles"
    )
    private Set<User> users = new HashSet<>();
}


