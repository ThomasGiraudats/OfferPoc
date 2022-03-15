package fr.af.offerpoc.validator.birthday;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;

/**
 * Implement Business Rules : It's impossible to save User with an age less than 18 (Adult)
 */
public class OfferBirthdayValidator implements ConstraintValidator<OfferBirthdayConstrainte, String> {

    @Override
    public void initialize(OfferBirthdayConstrainte constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String birthdate, ConstraintValidatorContext constraintValidatorContext) {


        Date date1= null;
        if (birthdate == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The birthdate must not be null").addConstraintViolation();
            return false;
        }else {
            try {
                date1 = new SimpleDateFormat("yyyyMMdd").parse(birthdate);
            } catch (ParseException e) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("The date of birth format must be YYYYMMDD").addConstraintViolation();
                return false;
            }
            Calendar a = getCalendar(date1);
            Calendar b = getCalendar(new Date());
            int diff = b.get(YEAR) - a.get(YEAR);
            if (a.get(MONTH) > b.get(MONTH) ||
                    (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
                diff--;
            }
            if (diff <18) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Creation of young people is impossible (age < 18 years)").addConstraintViolation();
                return false;
            }
        }
        return true;
    }


    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        cal.setTime(date);
        return cal;
    }
}
