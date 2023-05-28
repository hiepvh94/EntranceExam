package model.dto.response.user;

import lombok.*;
import model.entity.user.User;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponses {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String displayName;

    public static UserResponses fromUser(User user) {
        return UserResponses.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .displayName(String.join(user.getFirstName(), " ",user.getLastName()))
                .build();
    }
}
