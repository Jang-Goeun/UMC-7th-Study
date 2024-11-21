package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.StoreService.StoreCommandServiceImpl;
import umc.spring.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreCommandServiceImpl StoreCommandServiceImpl;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            addConstraintViolation(context, "Store cannot be null");
            return false;
        }

        // Service 계층을 통해 지역 존재 여부 확인
        boolean isValid;
        try {
            isValid = StoreCommandServiceImpl.doStoreExist(value);
        } catch (Exception e) {
            addConstraintViolation(context, ErrorStatus.STORE_NOT_FOUND.toString());
            return false;
        }

        return isValid;

    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
