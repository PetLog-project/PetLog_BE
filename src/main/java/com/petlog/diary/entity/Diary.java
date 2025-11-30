package com.petlog.diary.entity;

import com.petlog.common.entity.BaseEntity;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary")
@Entity
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_group_id", nullable = false, updatable = false)
    private PetGroup petGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private Member member;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "content", length = 3500, nullable = false)
    private String content;

    @Column(name = "written_at", nullable = false)
    private LocalDate writtenAt;

    @OneToMany(mappedBy = "diary")
    List<DiaryImage> images = new ArrayList<DiaryImage>();

    public Diary(
        final PetGroup petGroup,
        final Member member,
        final String title,
        final String content,
        final LocalDate writtenAt
    ) {
        this.petGroup = petGroup;
        this.member = member;
        this.title = title;
        this.content = content;
        this.writtenAt = writtenAt;
    }

    public void update(
        final String title,
        final String content,
        final LocalDate writtenAt
    ) {
        this.title = title;
        this.content = content;
        this.writtenAt = writtenAt;
    }
}
