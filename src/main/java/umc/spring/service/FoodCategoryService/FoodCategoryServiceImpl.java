package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    /**
     * 주어진 카테고리 ID 리스트가 모두 유효한지 확인
     *
     * @param categoryIds 카테고리 ID 리스트
     * @return 모든 카테고리가 존재하면 true, 아니면 false
     */
    @Override
    public boolean doCategoriesExist(List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return false; // null 또는 빈 리스트는 유효하지 않음
        }

        return categoryIds.stream().allMatch(foodCategoryRepository::existsById);
    }
}