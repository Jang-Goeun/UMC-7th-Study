package umc.spring.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.ReviewImage;

public interface ReviewImageRepository  extends JpaRepository<ReviewImage, String> {
}
