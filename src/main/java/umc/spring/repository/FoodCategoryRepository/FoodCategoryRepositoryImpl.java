package umc.spring.repository.FoodCategoryRepository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodCategoryRepositoryImpl implements FoodCategoryRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Long> findInvalidCategoryIds(List<Long> categoryIds) {
        String jpql = "SELECT id FROM FoodCategory WHERE id NOT IN :categoryIds";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("categoryIds", categoryIds);
        return query.getResultList();
    }
}