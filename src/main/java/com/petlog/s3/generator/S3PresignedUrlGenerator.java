package com.petlog.s3.generator;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.petlog.s3.service.dto.S3PresignedUrlItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class S3PresignedUrlGenerator implements PresignedUrlGenerator {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public List<S3PresignedUrlItem> generate(final List<String> keys) {
        List<S3PresignedUrlItem> list = new ArrayList<>();

        for (String key : keys) {
            final Date expiration = getExpiration();
            final GeneratePresignedUrlRequest request = getPostGeneratePresignedUrlRequest(key, expiration);
            final URL url = amazonS3Client.generatePresignedUrl(request);

            final S3PresignedUrlItem item = new S3PresignedUrlItem(key, url.toExternalForm());
            list.add(item);
        }

        return list;
    }

    private Date getExpiration() {
        final Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60; // 1시간
        expiration.setTime(expTimeMillis);

        return expiration;
    }

    private GeneratePresignedUrlRequest getPostGeneratePresignedUrlRequest(final String fileName, final Date expiration) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName)
            .withMethod(HttpMethod.PUT)
            .withKey(fileName)
            .withExpiration(expiration);

        generatePresignedUrlRequest.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        );

        return generatePresignedUrlRequest;
    }
}
