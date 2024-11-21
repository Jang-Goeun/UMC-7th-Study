package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class RegionHandler extends RuntimeException {
    public RegionHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
