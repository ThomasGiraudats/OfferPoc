package fr.af.offerpoc.validator.gender;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OfferGenderValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OfferGenderConstrainte {


        String message() default "Invalid Gender";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

}
