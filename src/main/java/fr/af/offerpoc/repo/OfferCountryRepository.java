package fr.af.offerpoc.repo;

import fr.af.offerpoc.entity.OfferCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfferCountryRepository extends JpaRepository<OfferCountry, Long> {

    OfferCountry findByCountryCode(String countryCode);

}
