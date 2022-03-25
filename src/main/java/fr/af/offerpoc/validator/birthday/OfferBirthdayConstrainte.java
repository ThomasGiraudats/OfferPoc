package fr.af.offerpoc.validator.birthday;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = OfferBirthdayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OfferBirthdayConstrainte {


    String message() default "Invalid birthdate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
