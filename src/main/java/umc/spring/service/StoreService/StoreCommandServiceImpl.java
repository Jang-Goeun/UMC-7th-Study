package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store registerStore(StoreRequestDTO.RegisterDto request){
        Long regionId = request.getRegion();

        // 유효한 Region ID라면 Region 객체를 설정
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        // Store 객체에 Region 설정
        Store newStore = StoreConverter.toStore(request, region);

        // Store 저장 및 반환
        return storeRepository.save(newStore);
    }
}

