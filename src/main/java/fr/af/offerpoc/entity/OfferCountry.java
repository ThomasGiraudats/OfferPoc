package fr.af.offerpoc.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/***
 * DTO bean for Country
 *
 */
@Data
@Builder
@Entity
@Table(name = "offer_country")
public class OfferCountry {
    @Id
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_seq")
    @GeneratedValue(generator = "country_id_generator")
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "country_label", nullable = false)
    private String countryLabel;


	
}
