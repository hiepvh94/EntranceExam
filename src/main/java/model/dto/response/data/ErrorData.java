package model.dto.response.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorData {
    private String field;

    private String message;
}
