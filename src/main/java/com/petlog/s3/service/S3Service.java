package com.petlog.s3.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.s3.generator.PresignedUrlGenerator;
import com.petlog.s3.service.dto.GeneratedS3PresignedUrlDto;
import com.petlog.s3.service.dto.S3PresignedUrlItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final PresignedUrlGenerator presignedUrlGenerator;
    private final MemberRepository memberRepository;

    public GeneratedS3PresignedUrlDto generateS3PresignedUrl(final Long memberId, final FileType fileType, final List<String> fileNames) {
        getMember(memberId);

        final List<String> keys = keys(memberId, fileType, fileNames);
        final List<S3PresignedUrlItem> items = presignedUrlGenerator.generate(keys);

        return new GeneratedS3PresignedUrlDto(items);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private List<String> keys(final Long memberId, final FileType fileType, final List<String> fileNames) {
        return fileNames.stream()
            .map(fileName -> "%d/%s/%s/%s".formatted(
                memberId,
                fileType,
                UUID.randomUUID(),
                fileName
            ))
            .toList();
    }
}
