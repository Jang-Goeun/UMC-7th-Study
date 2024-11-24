package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class PageHandler extends RuntimeException {
    public PageHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
