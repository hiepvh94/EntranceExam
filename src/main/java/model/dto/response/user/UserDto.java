package model.dto.response.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {
	
	private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String displayName;
}
