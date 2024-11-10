package umc.spring.repository.Review;

import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {
    void insertReview(Long memberId, Long storeId, String title, Float score);
}