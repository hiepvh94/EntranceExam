package model.dto.response.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtAuthenticationResponse {
    private String token;

    private UserResponses user;
}
