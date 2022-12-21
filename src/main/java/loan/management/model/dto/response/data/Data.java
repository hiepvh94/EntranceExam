package loan.management.model.dto.response.data;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class Data {
    private String message;

    private Integer status;

    private Boolean isSuccess;

    private Object results;

}
