package fr.af.offerpoc.repo;

import fr.af.offerpoc.entity.OfferUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferUserRepository extends JpaRepository<OfferUser, Long> {

    List<OfferUser> findByUserName(String userName);

}
