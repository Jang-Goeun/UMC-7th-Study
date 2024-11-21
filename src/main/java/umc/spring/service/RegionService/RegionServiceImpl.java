package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.RegionRepository.RegionRepository;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    /**
     * 주어진 regionId 지역 ID가 유효한지 확인
     *
     * @param regionId 지역 ID
     * @return 모든 카테고리가 존재하면 true, 아니면 false
     */
    @Override
    public boolean doRegionExist(Long regionId) {
        if (regionId == null) {
            return false; // null 유효하지 않음
        }

        return regionRepository.existsById(regionId);
    }
}