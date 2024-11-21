package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionRegisterDto{
        @ExistStore
        Long store;
        @NotBlank
        String missionSpec;
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
    }
}
