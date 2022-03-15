package fr.af.offerpoc.service;

import fr.af.offerpoc.entity.OfferCountry;
import fr.af.offerpoc.repo.OfferCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfferCountryService {
    private final OfferCountryRepository countryRepository;

    @Autowired
    public OfferCountryService(OfferCountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Optional<OfferCountry> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public List<OfferCountry> getCountryByCode(String code) {
        return countryRepository.findByCountryCode(code);
    }


}
