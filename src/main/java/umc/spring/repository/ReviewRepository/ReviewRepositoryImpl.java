package umc.spring.repository.ReviewRepository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.Store;
import umc.spring.domain.Review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public void insertReview(Long memberId, Long storeId, String title, Float score) {
        Member member = entityManager.getReference(Member.class, memberId);
        Store store = entityManager.getReference(Store.class, storeId);

        // Review 객체 생성 및 설정
        Review review = Review.builder()
                .member(member)
                .store(store)
                .title(title)
                .score(score)
                .build();

        // Review 엔티티 저장
        entityManager.persist(review);
    }


}
