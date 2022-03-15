package fr.af.offerpoc.validator.gender;

import fr.af.offerpoc.entity.OfferGenderEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * If the Gender is not null, it must be M or F
 */
public class OfferGenderValidator implements ConstraintValidator<OfferGenderConstrainte, String> {

    @Override
    public void initialize(OfferGenderConstrainte constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        if (gender == null) {
            return true;
        }else {
            if (OfferGenderEnum.getGenderByCode(gender) == null) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("The Gender Must be F (Female) or M (Male)").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
