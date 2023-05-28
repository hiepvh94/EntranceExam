package model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.AuditedEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "Users")
public class User extends AuditedEntity {



    @Column(nullable = false)
    private String password;

    @Email
    private String email;

    @Size(max = 50)
    private String firstName;
    
    @Size(max = 50)
    private String lastName;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Token token;


    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
			name = "UserInRole",
            joinColumns = {@JoinColumn(name = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "RoleId")}
    )
    Set<Role> roles = new HashSet<>();

}
