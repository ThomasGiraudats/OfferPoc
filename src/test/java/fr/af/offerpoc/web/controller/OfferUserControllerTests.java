package fr.af.offerpoc.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.af.offerpoc.commun.exception.OfferTechnicalException;
import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.model.OfferUserModel;
import fr.af.offerpoc.service.OfferCountryService;
import fr.af.offerpoc.service.OfferUserService;
import fr.af.offerpoc.utils.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OfferUserController.class)
public class OfferUserControllerTests {


    @MockBean
    OfferUserService userService;

    @MockBean
    OfferCountryService countryService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    OfferUserModel newUser;
    OfferUserModel newYoungUser;


    OfferUser newUserDto;
    OfferCountry frCountry;
    List<OfferUser> offerUsers;


    @Before
    public void setUp() throws OfferTechnicalException {
        newUser = TestHelper.buildFormUser();
        newYoungUser = TestHelper.buildFormYoungUser();
        newUserDto = TestHelper.buildUserDTO();
        offerUsers = new ArrayList<OfferUser>();
        offerUsers.add(newUserDto);
        frCountry= OfferCountry.builder()
                            .countryId(new Long(10))
                .countryCode("FR")
                .countryLabel("France").build();

    }

    @Test
    public void should_get_all_users() throws Exception {
        given(userService.getAllUsers()).willReturn(Arrays.asList(newUserDto));

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void should_get_user_by_id() throws Exception {
        given(userService.getUserById(newUserDto.getUserId())).willReturn(Optional.of(newUserDto));

        this.mockMvc
                .perform(get("/api/users/"+newUserDto.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(newUserDto.getUserName())));

    }

    @Test
    public void should_create_user() throws Exception {
        given(countryService.getCountryByCode("FR")).willReturn(frCountry);
        this.mockMvc
                .perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void create_young_user() throws Exception {
        given(countryService.getCountryByCode("FR")).willReturn(frCountry);
        this.mockMvc
                .perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newYoungUser))
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_delete_user() throws Exception {
        doNothing().when(userService).deleteUser(newUserDto.getUserId());

        this.mockMvc
                .perform(delete("/api/users/"+newUserDto.getUserId()))
                .andExpect(status().isOk());
    }


    @Test
    public void should_get_user_by_name() throws Exception {
        given(userService.getUserByName(newUser.getUserName())).willReturn(offerUsers);

        this.mockMvc
                .perform(get("/api/users/getUserByName?name="+newUser.getUserName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
