package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class StoreHandler extends RuntimeException {
    public StoreHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
