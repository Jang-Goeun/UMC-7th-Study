package umc.spring.repository.FoodCategoryRepository;

import java.util.List;

public interface FoodCategoryRepositoryCustom {
    List<Long> findInvalidCategoryIds(List<Long> categoryIds);
}
