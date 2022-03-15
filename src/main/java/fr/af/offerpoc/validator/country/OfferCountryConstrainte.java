package fr.af.offerpoc.validator.country;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OfferCountryValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OfferCountryConstrainte {


        String message() default "Invalid Country";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

}
