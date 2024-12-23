package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Review;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public void setReview(Review review){
        if(this.review != null)
            review.getReviewImageList().remove(this);
        this.review = review;
        review.getReviewImageList().add(this);
    }
}