package com.petlog.common.response;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getValue();
}
