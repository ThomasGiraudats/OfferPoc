package fr.af.offerpoc.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;


/***
 * Entity bean for Country
 * @Author TGI
 * @Date 24/03/2022
 */
@Entity
@Table(name = "offer_country")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferCountry {

    @Schema(description = "Unique identifier of the Country.",
            example = "1", required = true)
    @Id
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_seq")
    @GeneratedValue(generator = "country_id_generator")
    @Column(name = "country_id")
    private Long countryId;

    @Schema(description = "Code of the Country.",
            example = "FR", required = true)
    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Schema(description = "Label of the Country.",
            example = "France", required = true)
    @Column(name = "country_label", nullable = false)
    private String countryLabel;

}
