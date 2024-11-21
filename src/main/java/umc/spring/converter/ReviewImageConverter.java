package umc.spring.converter;

import umc.spring.domain.mapping.ReviewImage;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewImageConverter {
    public static List<ReviewImage> toReviewImageList(List<String> reviewImageList){

        return reviewImageList.stream()
                .map(reviewImage ->
                        ReviewImage.builder()
                                .imageUrl(reviewImage)
                                .build()
                ).collect(Collectors.toList());
    }
}
