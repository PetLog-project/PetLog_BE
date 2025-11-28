package com.petlog.petgroup.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PetGroupJoinCodeGeneratorTest {

    @DisplayName("6글자인 랜덤한 joinCode를 생성한다.")
    @Test
    void whenGeneratePetGroupJoinCode_thenSuccess() {
        // Given
        final PetGroupJoinCodeGenerator generator = new PetGroupJoinCodeGenerator();

        //When
        final String joinCode = generator.generate();

        //Then
        assertThat(joinCode.length()).isEqualTo(6);
    }
}