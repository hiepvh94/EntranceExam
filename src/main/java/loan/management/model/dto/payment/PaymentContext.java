package loan.management.model.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class PaymentContext {

    private PaymentMethodDTO paymentMethodDTO;

    public BigDecimal payment(BigDecimal interestRate, BigDecimal amount, BigDecimal paymentAmount){
        return this.paymentMethodDTO.payment(interestRate, amount, paymentAmount);
    }
}
