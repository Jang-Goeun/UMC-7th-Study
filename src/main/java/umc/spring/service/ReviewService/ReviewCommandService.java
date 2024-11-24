package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review registerReview(ReviewRequestDTO.RegisterDto request);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Review> getMyReviewList(Long MemberId, Integer page);
}
