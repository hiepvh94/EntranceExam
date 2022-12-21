package loan.management.model.dto.response.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtAuthenticationResponse {
    private String accessToken;

    private String tokenType;
}
