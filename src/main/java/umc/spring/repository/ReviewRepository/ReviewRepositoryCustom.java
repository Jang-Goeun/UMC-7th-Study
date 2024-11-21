package umc.spring.repository.ReviewRepository;

public interface ReviewRepositoryCustom {
    void insertReview(Long memberId, Long storeId, String title, Float score);
}