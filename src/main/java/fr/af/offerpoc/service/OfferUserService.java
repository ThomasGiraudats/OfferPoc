package fr.af.offerpoc.service;

import fr.af.offerpoc.entity.OfferUser;
import fr.af.offerpoc.repo.OfferUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfferUserService {
    private final OfferUserRepository userRepository;

    @Autowired
    public OfferUserService(OfferUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<OfferUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<OfferUser> getAllUsers() {
        return userRepository.findAll();
    }

    public OfferUser createUser(OfferUser user) {
        return userRepository.save(user);
    }

    public OfferUser updateUser(OfferUser user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public  List<OfferUser>  getUserByName(String name) {
        return userRepository.findByUserName(name);
    }

}
