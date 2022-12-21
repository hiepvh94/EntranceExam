package loan.management.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValue {

    Class<? extends Enum> enumClass();

    String value() default "";

    String message() default "must be any of enum {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}