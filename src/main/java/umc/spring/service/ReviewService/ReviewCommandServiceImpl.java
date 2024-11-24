package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.ReviewImageConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Store;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.ReviewImage;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewImageRepository.ReviewImageRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review registerReview(ReviewRequestDTO.RegisterDto request){
        Long storeId = request.getStore();
        Long memberId = request.getMember();

        // 유효한 storeId ID라면 Store 객체를 설정
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // 유효한 memberId ID라면 Member 객체를 설정
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // Review 객체에 Store, Member 설정
        Review newReview = ReviewConverter.toReview(request, store, member);
        List<ReviewImage> reviewImageUrlList = ReviewImageConverter.toReviewImageList(request.getReviewImageList());

        reviewImageUrlList.forEach(reviewImage -> {reviewImage.setReview(newReview);});

        // Review 저장 및 반환
        return reviewRepository.save(newReview);
    }


    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
}

