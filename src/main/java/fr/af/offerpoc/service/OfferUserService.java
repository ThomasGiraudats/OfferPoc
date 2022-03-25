package fr.af.offerpoc.service;

import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.repo.OfferUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



/**
 * DAO for user entity
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Service
@Transactional
public class OfferUserService {

    private final OfferCountryService offerCountryService;
    private final OfferUserRepository userRepository;

    @Autowired
    public OfferUserService(OfferCountryService offerCountryService, OfferUserRepository userRepository) {
        this.offerCountryService = offerCountryService;
        this.userRepository = userRepository;
    }

    /**
     * Return a OfferUser search by id
     * @param id : an id of user
     * @return Offer user (can be null)
     */
    public Optional<OfferUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Search all Users
     * @return List<OfferUser> : return all Users
     */
    public List<OfferUser> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Ceate a User
     * @param user OfferUser to create
     * @return New OfferUser
     */
    public OfferUser createUser(OfferUser user) {
        user.setCountry(offerCountryService.getCountryByCode(user.getCountry().getCountryCode()));
        return userRepository.save(user);
    }

    /**
     * Update user
     * @param user OfferUSer to update
     * @return OfferUser Updated
     */
    public OfferUser updateUser(OfferUser user) {
        return userRepository.save(user);
    }

    /**
     * Delete User
     * @param userId
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Search all user user by name parameter
     *
     * @param name
     * @return List<OfferUser> return a list of user matching with name parameter
     */
    public List<OfferUser> getUserByName(String name) {
        return userRepository.findByUserName(name);
    }

}
