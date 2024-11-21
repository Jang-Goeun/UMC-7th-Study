package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class MemberHandler extends RuntimeException {
    public MemberHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
