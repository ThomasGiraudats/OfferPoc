package fr.af.offerpoc.mapper;


import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.model.OfferUserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Utility for map Entity beau User with her model
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Mapper(
        componentModel = "spring"
)
public abstract class UserMapper {


    /**
     * Return an instance of userModel mapped from UserEntity
     * @param user OfferUser : User Entity
     * @return UserModel
     */
    @Mapping(target = "userBirthdate", dateFormat = "yyyyMMdd")
    @Mapping(source = "user.userGender.code", target = "userGender")
    @Mapping(source = "user.country.countryCode", target = "userCountry")
    public abstract OfferUserModel getModelFromEntity(OfferUser user);



    /**
     * Return an instance of UserEntity mapped from UserModel
     * @param userModel OfferUserModel : User model
     * @return User Entity ; OfferUser
     */
    @Mapping(target = "userBirthdate", dateFormat = "yyyyMMdd")
    @Mapping(source = "userModel.userGender", target = "userGender")
    @Mapping(source = "userModel.userCountry", target = "country.countryCode")
    public abstract OfferUser getEntityFromModel(OfferUserModel userModel);



}
