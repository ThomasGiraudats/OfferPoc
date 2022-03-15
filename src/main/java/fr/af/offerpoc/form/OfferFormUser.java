package fr.af.offerpoc.form;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.validator.birthday.OfferBirthdayConstrainte;
import fr.af.offerpoc.validator.country.OfferCountryConstrainte;
import fr.af.offerpoc.validator.gender.OfferGenderConstrainte;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Bean Used in Front
 */
@Data
@Builder
public class OfferFormUser {

    @NotEmpty(message = "User Name may not be empty.")
    private String userName;

    @OfferBirthdayConstrainte
    private String userBirthdate;

    @OfferGenderConstrainte
    private String userGender;

    private String userPhoneNumber;

    @OfferCountryConstrainte
    private String userCountry;

}
