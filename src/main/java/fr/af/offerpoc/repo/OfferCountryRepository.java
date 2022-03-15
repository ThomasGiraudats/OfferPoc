package fr.af.offerpoc.repo;

import fr.af.offerpoc.entity.OfferCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferCountryRepository extends JpaRepository<OfferCountry, Long> {

    List<OfferCountry> findByCountryCode(String countryCode);

}
