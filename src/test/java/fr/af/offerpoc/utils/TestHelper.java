package fr.af.offerpoc.utils;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.entity.OfferGenderEnum;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.form.OfferFormUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;

public class TestHelper {
    public static OfferFormUser buildFormUser() {
        String uuid = UUID.randomUUID().toString();
        return OfferFormUser.builder()
                .userName("name-"+uuid)
                .userCountry("FR")
                .userGender("M")
                .userBirthdate("20000101")
                .build();
    }


    public static OfferUser buildUserDTO()  {

        OfferCountry offerCountry = OfferCountry.builder()
                .countryId(new Long(10))
                .countryCode("FR")
                .countryLabel("France").build();
          try {
            Date dateBirthdDate = new SimpleDateFormat("yyyMMdd").parse("20100101");
              String uuid = UUID.randomUUID().toString();
              return OfferUser.builder()
                      .userId(new Long(1))
                      .userName("name-"+uuid)
                      .userGender(OfferGenderEnum.MALE)
                      .userBirthdate(dateBirthdDate)
                      .country(offerCountry)
                      .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return null;
    }
}
