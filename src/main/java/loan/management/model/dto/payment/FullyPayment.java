package loan.management.model.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class FullyPayment extends PaymentMethodDTO{

    @Override
    BigDecimal payment(BigDecimal interestRate, BigDecimal amount, BigDecimal paymentAmount) {
        interestRate = BigDecimal.ONE;
//        return amount.multiply(interestRate).subtract(paymentAmount);
        return amount.subtract(paymentAmount).add(interestRate);
    }
}
