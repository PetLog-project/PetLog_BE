package com.petlog.diary.entity;

import com.petlog.common.entity.BaseEntity;
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
@Table(name = "diary_image")
@Entity
public class DiaryImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false, updatable = false)
    private Diary diary;

    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;

    public DiaryImage(
        final Diary diary,
        final String imageUrl
    ) {
        this.diary = diary;
        this.imageUrl = imageUrl;
    }
}
