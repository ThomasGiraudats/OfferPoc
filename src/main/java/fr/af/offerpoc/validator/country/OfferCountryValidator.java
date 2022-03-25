package fr.af.offerpoc.validator.country;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.service.OfferCountryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Business rule :
 * Only French resident are Allow to create an account
 * @Author TGI
 * @Date 24/03/2022
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

    /**
     * Valid the country of user :
     * BR -> Must have a contry value
     * BR2 -> Only French user
     *
     * @param country
     * @param constraintValidatorContext
     * @return boolean true if it's valid
     */
    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {
        if (country == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The country must not be null").addConstraintViolation();
            return false;
        } else {
            OfferCountry countryEntity = countryService.getCountryByCode(country);
            if (countryEntity != null && countryEntity.getCountryCode().equals("FR")) {
                return true;
            } else {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("The country code : " + country + " is not autorised to creat an account.").addConstraintViolation();
                return false;
            }

    }

}


}
