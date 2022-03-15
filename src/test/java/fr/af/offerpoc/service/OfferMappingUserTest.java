package fr.af.offerpoc.service;

import fr.af.offerpoc.commun.exception.OfferTechnicalException;
import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.entity.OfferMappingUser;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.form.OfferFormUser;
import fr.af.offerpoc.utils.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferMappingUserTest {

    @Autowired
    OfferMappingUser mappingUser;

    @MockBean
    OfferCountryService countryService;
    OfferFormUser newUser;

    OfferUser newUserDto;
    List<OfferCountry> offerCountryList;

    @Before
    public void setUp() throws OfferTechnicalException {
        newUser = TestHelper.buildFormUser();
        newUserDto = TestHelper.buildUserDTO();
        offerCountryList = new ArrayList<OfferCountry>();
        OfferCountry offerCountry = OfferCountry.builder()
                .countryId(new Long(10))
                .countryCode("FR")
                .countryLabel("France").build();
        offerCountryList.add(offerCountry);

    }

    @Test
    public void mappingUserDTO() throws Exception {
        given(countryService.getCountryByCode("FR")).willReturn(offerCountryList);

        OfferFormUser userForm = mappingUser.mappingUserFromDTO(newUserDto);
        assertThat(userForm.getUserName()).isEqualTo(newUserDto.getUserName());
    }


    @Test
    public void mappingUserForm() throws Exception {
        OfferUser userDto = mappingUser.mappingUserDtoFromForm(newUser);
        assertThat(userDto.getUserName()).isEqualTo(newUser.getUserName());
    }

}
