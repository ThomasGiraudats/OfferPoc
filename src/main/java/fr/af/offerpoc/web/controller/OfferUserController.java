package fr.af.offerpoc.web.controller;

import fr.af.offerpoc.commun.exception.OfferTechnicalException;
import fr.af.offerpoc.entity.OfferMappingUser;
import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.form.OfferFormUser;
import fr.af.offerpoc.service.OfferUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * MAIN Controler
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class OfferUserController {

    private final OfferMappingUser mappingUser;
    private final OfferUserService userService;

    @Autowired
    public OfferUserController(OfferMappingUser mappingUser, OfferUserService userService) {
        this.mappingUser = mappingUser;
        this.userService = userService;
    }


    @GetMapping("")
    public List<OfferUser> getUsers() {
        log.info("process=get-users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferUser> getUser(@PathVariable Long id) {
        log.info("process=get-user, user_id={}", id);
        Optional<OfferUser> user = userService.getUserById(id);
        return user.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public OfferFormUser createUser(@RequestBody @Validated OfferFormUser user, HttpServletResponse response) {
        log.info("process=create-user, user_name={}", user.getUserName());
        //TODO : Use an Dozer Mapping
        try {
            OfferUser offerUser = userService.createUser(mappingUser.mappingUserDtoFromForm(user));
            return mappingUser.mappingUserFromDTO(offerUser);
        } catch (OfferTechnicalException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(), e);
        }

    }
    @GetMapping("/getUserByName")
    public List<OfferUser> getUserByName(@RequestParam String name) {
        //  log.info("process=update-user, user_id={}", id);
        return userService.getUserByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("process=delete-user, user_id={}", id);
        userService.deleteUser(id);
    }

}
