package umc.spring.repository.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Review;

public interface ReviewRepository  extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
