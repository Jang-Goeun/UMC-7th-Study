package umc.spring.service.MissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission registerMission(MissionRequestDTO.@Valid MissionRegisterDto request){
        Long storeId = request.getStore();

        // 유효한 storeId ID라면 Store 객체를 설정
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Mission 객체에 Store 설정
        Mission newMission = MissionConverter.toMission(request, store);

        // Review 저장 및 반환
        return missionRepository.save(newMission);
    }

    /**
     * 주어진 missionId 미션 ID가 유효한지 확인
     *
     * @param missionId 미션 ID
     * @return 미션 존재하면 true, 아니면 false
     */
    @Override
    public boolean doMissionExist(Long missionId) {
        if (missionId == null) {
            return false; // null 유효하지 않음
        }

        return missionRepository.existsById(missionId);
    }
}

