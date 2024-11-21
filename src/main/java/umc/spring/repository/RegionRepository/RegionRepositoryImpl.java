package umc.spring.repository.RegionRepository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Long findInvalidRegionIds(Long regionId) {
        String jpql = "SELECT r.id FROM Region r WHERE r.id != :regionId";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("regionId", regionId);
        return query.getSingleResult();
    }
}