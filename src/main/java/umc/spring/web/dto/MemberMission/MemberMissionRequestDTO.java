package umc.spring.web.dto.MemberMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;

public class MemberMissionRequestDTO {

    @Getter
    public static class ChallengingDto{
        @NotNull
        Integer status;
        @ExistMission
        Long mission;
        @ExistMember
        Long member;
    }
}
