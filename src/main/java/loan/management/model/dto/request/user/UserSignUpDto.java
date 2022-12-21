package loan.management.model.dto.request.user;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserSignUpDto {
    @Size(min = 4, max = 30)
    private String fullName;


    @NotNull(message = "Please provide a username")
    @Column(unique = true)
    @Size(min = 6, max = 15)
    private String username;

    @Email(message = "The email is not properly formatted")
    @Size(min = 1, max = 40)
    private String email;

    @Size(min = 1, max = 20)
    private String mobile;

    @NotNull(message = "Please provide a pasword")
    @Column(unique = true)
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
