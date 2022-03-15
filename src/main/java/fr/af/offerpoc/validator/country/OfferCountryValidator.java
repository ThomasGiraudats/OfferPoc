package fr.af.offerpoc.validator.country;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.service.OfferCountryService;
import fr.af.offerpoc.service.OfferUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Calendar.*;

/**
 * Business rule :
 * Only French resident are Allow to create an account
 */
public class OfferCountryValidator implements ConstraintValidator<OfferCountryConstrainte, String> {
    private final OfferCountryService countryService;

    @Autowired
    public OfferCountryValidator(OfferCountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void initialize(OfferCountryConstrainte constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {
        if (country == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The country must not be null").addConstraintViolation();
            return false;
        }else {
            List<OfferCountry> listCountry =  countryService.getCountryByCode(country);
            if (listCountry != null && listCountry.size() > 0) {
                if (listCountry.get(0).getCountryCode().equals("FR")) {
                    return true;
                }else {
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate("The country code : "+country+ " is not autorised to creat an account.").addConstraintViolation();
                    return false;
                }

            }else {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("The country code : " + country + " is unknown.").addConstraintViolation();
                return false;
            }
        }

    }



}
