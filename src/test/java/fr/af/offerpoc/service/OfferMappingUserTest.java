package fr.af.offerpoc.service;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.mapper.UserMapper;
import fr.af.offerpoc.model.OfferUserModel;
import fr.af.offerpoc.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@ContextConfiguration
public class OfferMappingUserTest {


    UserMapper userMapper;


    @MockBean
    OfferCountryService countryService;
    OfferUserModel newUser;

    OfferUser newUserDto;
    OfferCountry frCountry;



    @BeforeEach
    public void setUp()  {
        userMapper=  Mappers.getMapper(UserMapper.class);
        newUser = TestHelper.buildFormUser();
        newUserDto = TestHelper.buildUserDTO();
        frCountry = OfferCountry.builder()
                .countryId(new Long(10))
                .countryCode("FR")
                .countryLabel("France").build();

    }

    @Test
    public void mappingUserDTOTest()  {
        given(countryService.getCountryByCode("FR")).willReturn(frCountry);

        OfferUserModel userForm = userMapper.getModelFromEntity(newUserDto);
        assertThat(userForm.getUserName()).isEqualTo(newUserDto.getUserName());
        assertThat(userForm.getUserCountry()).isEqualTo(newUserDto.getCountry().getCountryCode());
    }


    @Test
    public void mappingUserFormTest()  {
        OfferUser userDto = userMapper.getEntityFromModel(newUser);
        assertThat(userDto.getUserName()).isEqualTo(newUser.getUserName());
        assertThat(userDto.getCountry().getCountryCode()).isEqualTo(newUser.getUserCountry());
    }

}
