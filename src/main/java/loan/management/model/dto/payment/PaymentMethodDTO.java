package loan.management.model.dto.payment;

import java.math.BigDecimal;

public abstract class PaymentMethodDTO {

    abstract BigDecimal payment(BigDecimal interestRate, BigDecimal amount, BigDecimal paymentAmount);
}
