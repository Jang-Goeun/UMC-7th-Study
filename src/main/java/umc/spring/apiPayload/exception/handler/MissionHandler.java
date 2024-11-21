package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class MissionHandler extends RuntimeException {
    public MissionHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
