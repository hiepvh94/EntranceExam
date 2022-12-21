package loan.management.model.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class PartiallyPayment extends PaymentMethodDTO{

    @Override
    BigDecimal payment(BigDecimal interestRate, BigDecimal amount, BigDecimal paymentAmount) {
//        return amount.multiply(interestRate).add(paymentAmount);
        return amount.subtract(paymentAmount);
    }
}
