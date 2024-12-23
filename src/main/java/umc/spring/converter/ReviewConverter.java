package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 내가 작성한 리뷰
    public static ReviewResponseDTO.MyReviewDTO myReviewDTO(Review review){
        return ReviewResponseDTO.MyReviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO myReviewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.MyReviewDTO> myReviewDTOList = reviewList.stream()
                .map(ReviewConverter::myReviewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(myReviewDTOList.size())
                .reviewList(myReviewDTOList)
                .build();
    }

    // 특정 가게 리뷰 목록
    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
