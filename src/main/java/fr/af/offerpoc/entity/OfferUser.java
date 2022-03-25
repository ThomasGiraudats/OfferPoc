package fr.af.offerpoc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.EnumType;

/**
 * Entity bean for User
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Entity
@Table(name = "offer_user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferUser {



    @Schema(description = "Unique identifier of User.",
            example = "1", required = true)
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    @GeneratedValue(generator = "user_id_generator")
    @Column(name = "user_id")
    private Long userId;


  @Schema(description = "Name of User.",
            example = "Bertrand", required = true)
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Schema(description = "birthdate of User.",
            example = "", required = true)
    @Column(name = "user_birthdate", nullable = false)
    private LocalDate userBirthdate;



    @Schema(description = "identifier of country.",
            example = "", required = true)
    @ManyToOne
    @JoinColumn(name = "user_country_id")
    private OfferCountry country;



    @Schema(description = "Phone Number.",
            example = "", required = true)
    @Column(name = "user_phone")
    private String userPhone;

    @Schema(description = "Phone Number.",
            example = "", required = true)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_gender")
    private OfferGenderEnum userGender;

    @JsonProperty("created_at")
    private LocalDateTime userCreatedAt;
    @JsonProperty("updated_at")
    private LocalDateTime userUpdatedAt;

    @PrePersist
    void preSave() {
        if (userCreatedAt == null) {
            userCreatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    void preUpdate() {
        if (userUpdatedAt == null) {
            userUpdatedAt = LocalDateTime.now();
        }
    }


}
