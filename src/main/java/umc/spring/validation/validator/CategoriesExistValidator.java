package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.service.FoodCategoryService.FoodCategoryServiceImpl;


import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryServiceImpl FoodCategoryServiceImpl;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            addConstraintViolation(context, "Category list cannot be null or empty");
            return false;
        }

        // Service 계층을 통해 카테고리 존재 여부 확인
        boolean isValid;
        try {
            isValid = FoodCategoryServiceImpl.doCategoriesExist(values);
        } catch (Exception e) {
            addConstraintViolation(context, ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString());
            return false;
        }

        return isValid;

    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
