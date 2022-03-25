package fr.af.offerpoc.model;

import fr.af.offerpoc.validator.birthday.OfferBirthdayConstrainte;
import fr.af.offerpoc.validator.country.OfferCountryConstrainte;
import fr.af.offerpoc.validator.gender.OfferGenderConstrainte;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Bean Used in Front
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Data
@Builder
public class OfferUserModel {
    @Schema(description = "User Name.",
            example = "", required = true)
    @NotEmpty(message = "User Name may not be empty.")
    private String userName;

    @Schema(description = "User Birthdate.",
            example = "", required = true)
    @OfferBirthdayConstrainte
    private String userBirthdate;

    @Schema(description = "User Gender.",
            example = "", required = false)
    @OfferGenderConstrainte
    private String userGender;

    @Schema(description = "User Phone.",
            example = "", required = false)
    private String userPhoneNumber;

    @Schema(description = "Country of user.",
            example = "", required = true)
    @OfferCountryConstrainte
    private String userCountry;

}
