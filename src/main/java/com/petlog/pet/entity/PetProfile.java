package com.petlog.pet.entity;

import com.petlog.common.entity.BaseEntity;
import com.petlog.petgroup.entity.PetGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet_profile")
@Entity
public class PetProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_group_id", nullable = false, updatable = false)
    private PetGroup petGroup;

    @Column(name = "image_url", length = 500, nullable = true)
    private String imageUrl;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "age", length = 10, nullable = false)
    private String age;

    @Column(name = "gender", length = 10, nullable = false)
    private String gender;

    @Column(name = "weight", length = 10, nullable = false)
    private String weight;

    @Column(name = "feeding_cycle", nullable = false)
    private int feedingCycle;

    @Column(name = "watering_cycle", nullable = false)
    private int wateringCycle;

    public PetProfile(
        final PetGroup petGroup,
        final String imageUrl,
        final String name,
        final String age,
        final String gender,
        final String weight,
        final int feedingCycle,
        final int wateringCycle
    ) {
        this.petGroup = petGroup;
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.feedingCycle = feedingCycle;
        this.wateringCycle = wateringCycle;
    }
}
