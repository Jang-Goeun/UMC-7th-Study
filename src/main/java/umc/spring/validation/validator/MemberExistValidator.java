package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberService.MemberCommandServiceImpl;
import umc.spring.validation.annotation.ExistMember;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long> {

    private final MemberCommandServiceImpl MemberCommandServiceImpl;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            addConstraintViolation(context, "Member cannot be null");
            return false;
        }

        // Service 계층을 통해 지역 존재 여부 확인
        boolean isValid;
        try {
            isValid = MemberCommandServiceImpl.doMemberExist(value);
        } catch (Exception e) {
            addConstraintViolation(context, ErrorStatus.MEMBER_NOT_FOUND.toString());
            return false;
        }

        return isValid;

    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
