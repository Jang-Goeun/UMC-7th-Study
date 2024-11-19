package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
