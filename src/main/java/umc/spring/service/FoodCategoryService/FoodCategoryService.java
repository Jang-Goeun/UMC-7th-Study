package umc.spring.service.FoodCategoryService;

import java.util.List;

public interface FoodCategoryService {
    boolean doCategoriesExist(List<Long> categoryIds);
}