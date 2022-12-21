package loan.management.model.dto.request.facility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FacilityRequest {

    private String identityCode;

    private String firstName;

    private String lastName;

    private String idCode;

    private String currency;

}
