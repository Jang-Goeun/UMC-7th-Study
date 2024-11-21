package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;

public class ReviewRequestDTO {
    @Getter
    public static  class RegisterDto{
        @ExistStore
        Long store;
        @ExistMember
        Long member;
        @NotNull
        Float score;
        @NotBlank
        String title;
        @NotEmpty(message = "리뷰 이미지는 최소 하나 이상이어야 합니다.")
        List<String> reviewImageList;
    }
}
