package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.RegionService.RegionServiceImpl;
import umc.spring.validation.annotation.ExistRegion;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionServiceImpl RegionServiceImpl;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            addConstraintViolation(context, "Region cannot be null");
            return false;
        }

        // Service 계층을 통해 지역 존재 여부 확인
        boolean isValid;
        try {
            isValid = RegionServiceImpl.doRegionExist(value);
        } catch (Exception e) {
            addConstraintViolation(context, ErrorStatus.REGION_NOT_FOUND.toString());
            return false;
        }

        return isValid;

    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
