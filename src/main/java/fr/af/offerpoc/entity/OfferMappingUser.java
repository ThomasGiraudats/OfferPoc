package fr.af.offerpoc.entity;

import fr.af.offerpoc.commun.exception.OfferTechnicalException;
import fr.af.offerpoc.form.OfferFormUser;
import fr.af.offerpoc.service.OfferCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *Service for mapping User Bean DTO with an fom Form User  (from Front)
 * @Author TGI
 * @Date 13/03/2022
 *
 */
@Service
public class OfferMappingUser {

    private final static String dateFormat = "yyyyMMdd";
    private final OfferCountryService countryService;

    @Autowired
    public OfferMappingUser(OfferCountryService countryService) {
        this.countryService = countryService;
    }



    public OfferFormUser mappingUserFromDTO(Optional<OfferUser> offerUser) throws OfferTechnicalException {
        return mappingUserFromDTO(offerUser.get());
    }
    /**
     * Mapping OfferUser -> OfferFormUser
     * @param offerUser
     * @return
     */
    public OfferFormUser mappingUserFromDTO(OfferUser offerUser) throws OfferTechnicalException {
        String codeCountry = "";
        if (offerUser.getCountry() != null){
            codeCountry = offerUser.getCountry().getCountryCode();
        }
        String codeGender = "";
        if (offerUser.getUserGender() != null ) {
            codeGender = offerUser.getUserGender().getCode();
        }
        OfferFormUser userForm = null;
        try {
            userForm = OfferFormUser.builder()
                    .userName(offerUser.getUserName())
                    .userCountry(codeCountry)
                    .userBirthdate(parseDate(offerUser.getUserBirthdate()))
                    .userPhoneNumber(offerUser.getUserPhone())
                    .userGender(codeGender)
                    .build();
        } catch (ParseException e) {
           throw new OfferTechnicalException("Convertion Date error ",e);
        }

        return userForm;

    }

    /**
     *
     * @param userForm
     * @return
     * @throws OfferTechnicalException
     */
    public OfferUser mappingUserDtoFromForm(OfferFormUser userForm) throws OfferTechnicalException{
        OfferCountry country = null;
        List<OfferCountry> listCountry = countryService.getCountryByCode(userForm.getUserCountry());
        if (listCountry != null && listCountry.size()> 0){
            country = listCountry.get(0);
        }
        OfferUser offerUser;
        try {
        offerUser = OfferUser.builder()
                    .userName(userForm.getUserName())
                    .userGender(OfferGenderEnum.getGenderByCode(userForm.getUserGender()))
                    .userBirthdate(parseDate(userForm.getUserBirthdate()))
                    .country(country)
                    .userPhone(userForm.getUserPhoneNumber())
                    .build();
        } catch (ParseException e) {
            throw new OfferTechnicalException("Convertion Date error ",e);
        }

        return offerUser;

    }




    /**
     * Convert String to date with standard format
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate (String date) throws ParseException {
       return new SimpleDateFormat(dateFormat).parse(date);
    }



    /**
     * Convert date to String with standard format
     * @param date
     * @return
     * @throws ParseException
     */
    public static String parseDate (Date date) throws ParseException {
        return new SimpleDateFormat(dateFormat).format(date);
    }
}
