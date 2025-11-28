package com.petlog.petgroup.generator;

import java.security.SecureRandom;

public class PetGroupJoinCodeGenerator implements RandomJoinCodeGenerator {

    private static final int JOIN_CODE_LENGTH = 6;
    private static final String JOIN_CODE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        final SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < JOIN_CODE_LENGTH; i++) {
            final int index = secureRandom.nextInt(JOIN_CODE_CHARACTERS.length());
            sb.append(JOIN_CODE_CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
