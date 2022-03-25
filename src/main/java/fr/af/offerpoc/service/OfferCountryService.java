package fr.af.offerpoc.service;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.repo.OfferCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * DAO for country
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Service
@Transactional
public class OfferCountryService {
    private final OfferCountryRepository countryRepository;

    @Autowired
    public OfferCountryService(OfferCountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Search country by id
     * @param id
     * @return OfferCountry can be null
     */
    public Optional<OfferCountry> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    /**
     * Search country by code
     *
     * @param code
     * @return OfferCountry can be null
     */
    public OfferCountry getCountryByCode(String code) {
        return countryRepository.findByCountryCode(code);
    }


}
