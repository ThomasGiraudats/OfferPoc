package fr.af.offerpoc.utils;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.entity.OfferGenderEnum;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.model.OfferUserModel;

import java.time.LocalDate;
import java.util.UUID;

public class TestHelper {
    public static OfferUserModel buildFormUser() {
        String uuid = UUID.randomUUID().toString();
        return OfferUserModel.builder()
                .userName("name-" + uuid)
                .userCountry("FR")
                .userGender("M")
                .userBirthdate("20000101")
                .build();
    }

    public static OfferUserModel buildFormYoungUser() {
        String uuid = UUID.randomUUID().toString();
        return OfferUserModel.builder()
                .userName("name-" + uuid)
                .userCountry("FR")
                .userGender("M")
                .userBirthdate("20100101")
                .build();
    }



    public static OfferUser buildUserDTO() {

        OfferCountry offerCountry = OfferCountry.builder()
                .countryId(new Long(10))
                .countryCode("FR")
                .countryLabel("France").build();
        LocalDate dateBirthdDate = LocalDate.of(2010, 01, 01);
        String uuid = UUID.randomUUID().toString();
        return OfferUser.builder()
                .userId(new Long(1))
                .userName("name-" + uuid)
                .userGender(OfferGenderEnum.M)
                .userBirthdate(dateBirthdDate)
                .country(offerCountry)
                .build();
    }
}
