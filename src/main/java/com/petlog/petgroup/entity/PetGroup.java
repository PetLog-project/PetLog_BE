package com.petlog.petgroup.entity;

import com.petlog.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet_group")
@Entity
public class PetGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "join_code", length = 20, nullable = false, unique = true)
    private String joinCode;

    @Column(name = "note", length = 1500, nullable = true)
    private String note;

    public PetGroup(
        final String joinCode,
        final String note
    ) {
        this.joinCode = joinCode;
        this.note = note;
    }
}
