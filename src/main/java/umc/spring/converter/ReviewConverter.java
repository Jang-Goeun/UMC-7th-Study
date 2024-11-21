package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDTO.RegisterResultDTO toRegisterResultDTO(Review review){
        return ReviewResponseDTO.RegisterResultDTO.builder()
                .reviewId(review.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.RegisterDto request, Store store, Member member){
        return Review.builder()
                .store(store)
                .member(member)
                .score(request.getScore())
                .title(request.getTitle())
                .ReviewImageList(new ArrayList<>())
                .build();
    }
}
