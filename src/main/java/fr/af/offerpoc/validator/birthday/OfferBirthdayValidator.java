package fr.af.offerpoc.validator.birthday;

import fr.af.offerpoc.commun.OfferUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


/**
 * Implement Business Rules : It's impossible to save User with an age less than 18 (Adult)
 *
 * @Author TGI
 * @Date 24/03/2022
 */
public class OfferBirthdayValidator implements ConstraintValidator<OfferBirthdayConstrainte, String> {

    @Override
    public void initialize(OfferBirthdayConstrainte constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * birthdate validation :
     * BR  -> age over 18 years
     * @param birthdate String date at format : yyyyMMdd
     * @param constraintValidatorContext
     * @return boolean true if it's valid
     */
    @Override
    public boolean isValid(String birthdate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate date1 = null;
        if (birthdate == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The birthdate must not be null").addConstraintViolation();
            return false;
        } else {

            try {
                date1 = OfferUtil.parseDate(birthdate);
            } catch (DateTimeParseException e) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("The date of birth format must be YYYYMMDD").addConstraintViolation();
                return false;
            }
            LocalDate today = LocalDate.now();
            long duration = ChronoUnit.YEARS.between(date1, today);

            if (duration < 18) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Creation of young people is impossible (age < 18 years)").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
