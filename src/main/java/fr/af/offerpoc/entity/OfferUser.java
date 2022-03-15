package fr.af.offerpoc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  DTO bean for User
 */
@Data
@Builder
@Entity
@Table(name = "offer_user")
public class OfferUser {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    @GeneratedValue(generator = "user_id_generator")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_birthdate", nullable = false)
    private Date userBirthdate;

    @ManyToOne
    @JoinColumn(name = "user_country_id")
    private OfferCountry country;

    @Column(name = "user_phone")
    private String userPhone;

    @Enumerated(EnumType.ORDINAL)
    private OfferGenderEnum userGender;

    @JsonProperty("created_at")
    private LocalDateTime userCreatedAt;
    @JsonProperty("updated_at")
    private LocalDateTime userUpdatedAt;

    @PrePersist
    void preSave() {
        if(userCreatedAt == null) {
            userCreatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    void preUpdate() {
        if(userUpdatedAt == null) {
            userUpdatedAt = LocalDateTime.now();
        }
    }

	
}
