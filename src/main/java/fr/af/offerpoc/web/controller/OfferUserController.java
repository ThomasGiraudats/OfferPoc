package fr.af.offerpoc.web.controller;

import fr.af.offerpoc.commun.exception.OfferUserNotFoundException;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.mapper.UserMapper;
import fr.af.offerpoc.model.OfferUserModel;
import fr.af.offerpoc.service.OfferUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * MAIN Controler
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "user", description = "the User API")
@Slf4j
public class OfferUserController {

    private final UserMapper userMapper;

    private final OfferUserService userService;

    @Autowired
    public OfferUserController(OfferUserService userService) {
        this.userMapper = Mappers.getMapper(UserMapper.class);
        this.userService = userService;
    }

    @Operation(summary = "Find All User", description = "Returns a list of user", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OfferUser.class)))) })
    @GetMapping("")
    public List<OfferUserModel> getUsers() {
        return userService.getAllUsers().stream().map(userMapper::getModelFromEntity).collect(Collectors.toList());
    }

    @Operation(summary = "Find user by ID", description = "Returns a single user", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = OfferUser.class))),
            @ApiResponse(responseCode = "404", description = "User not found") })
    @GetMapping("/{id}")
    public OfferUserModel getUser(@PathVariable Long id) {
        Optional<OfferUser> userEntity = userService.getUserById(id);
        if (!userEntity.isPresent() )  throw new OfferUserNotFoundException();
        OfferUserModel user  = userMapper.getModelFromEntity(userEntity.get());
        return  user;
    }

    @Operation(summary = "Create User", description = "Returns a user created", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = OfferUser.class))),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PostMapping("")
    @ResponseStatus(CREATED)
    public OfferUserModel createUser(@RequestBody @Validated OfferUserModel user, HttpServletResponse response) {
            OfferUser offerUser = userService.createUser(userMapper.getEntityFromModel(user));
            return userMapper.getModelFromEntity(offerUser);
    }


   @Operation(summary = "Find user by Name", description = "Returns a single user", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = OfferUser.class))),
            @ApiResponse(responseCode = "404", description = "User not found") })
    @GetMapping("/getUserByName")
    public List<OfferUserModel> getUserByName(@RequestParam String name) {
        List<OfferUser> listUserEntity =  userService.getUserByName(name);
        if (null == listUserEntity || listUserEntity.size() ==0) throw new OfferUserNotFoundException();
         return userService.getUserByName(name).stream().map(userMapper::getModelFromEntity).collect(Collectors.toList());
    }

    @Operation(summary = "Find user by ID", description = "Returns a single user", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = OfferUser.class))),
            @ApiResponse(responseCode = "404", description = "User not found") })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        Optional<OfferUser> userEntity = userService.getUserById(id);
        if (!userEntity.isPresent() )  throw new OfferUserNotFoundException();
        userService.deleteUser(id);
    }

}
