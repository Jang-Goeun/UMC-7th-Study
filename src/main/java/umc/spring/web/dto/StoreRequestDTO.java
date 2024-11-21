package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistRegion;

public class StoreRequestDTO {

    @Getter
    public static class RegisterDto{
        @NotBlank
        String name;
        @Size(min = 5, max = 30)
        String address;
        @ExistRegion
        Long region;
    }
}
