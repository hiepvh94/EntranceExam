package model.dto.request.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserLoginDto {
    @NotBlank(message = "Please provide a username or email")
    private String email;

    @NotBlank(message = "Please provide a pasword")
    @Size(min = 6, max = 20)
    private String password;
}
