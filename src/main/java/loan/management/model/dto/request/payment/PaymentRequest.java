package loan.management.model.dto.request.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentRequest {

    private String facilityCode;
    private String loanType;
    private BigDecimal amount;
    private String paymentType;

}
