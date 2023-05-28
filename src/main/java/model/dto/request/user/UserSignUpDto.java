package model.dto.request.user;

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
    private String firstName;
    
    @Size(min = 4, max = 30)
    private String lastName;

    @Email(message = "The email is not properly formatted")
    @Size(min = 1, max = 40)
    private String email;

    @NotNull(message = "Please provide a pasword")
    @Column(unique = true)
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
