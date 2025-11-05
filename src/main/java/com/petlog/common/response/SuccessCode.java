package com.petlog.common.response;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    HttpStatus getValue();
    String getMessage();
}
