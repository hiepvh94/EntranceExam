package model.dto.response.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponseData {
    private Boolean success;
    private String message;
}
