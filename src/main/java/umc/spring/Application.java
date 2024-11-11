package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.ReviewService.ReviewService;
import umc.spring.domain.Mission;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			ReviewService reviewService = context.getBean(ReviewService.class);
			MemberQueryService memberService = context.getBean(MemberQueryService.class);
			MissionQueryService missionService = context.getBean(MissionQueryService.class);
/*
			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);

			// 리뷰 데이터 삽입 테스트
			Long memberId = 1L; // 실제 데이터베이스에 존재하는 Member ID로 설정
			Long storeId = 1L; // 실제 데이터베이스에 존재하는 Store ID로 설정
			String title = "맛있어요!";
			Float score = 4.5f;

			System.out.println("Inserting review:");
			System.out.println("Member ID: " + memberId);
			System.out.println("Store ID: " + storeId);
			System.out.println("Title: " + title);
			System.out.println("Score: " + score);

			// 리뷰 저장 메서드 호출
			reviewService.insertReview(memberId, storeId, title, score);
			System.out.println("Review inserted successfully.");


			// 마이페이지 테스트할 memberId 설정
			Long memberId = 2L;

			// 쿼리 메서드 호출 및 결과 출력
			System.out.println("Executing getMemberProfile with memberId: " + memberId);
			memberService.getMemberProfile(memberId).forEach(System.out::println);

			// 홈화면 테스트 파라미터 설정
			Long regionId = 1L;  // 서울의 ID
			Long memberId = 1L;  // 특정 사용자 ID
			String cursorValue = null;  // 커서 값 설정
			int pageSize = 5;  // 가져올 데이터 개수

			// 쿼리 호출 및 출력
			missionService.getMissionsByRegionAndMember(regionId, memberId, cursorValue, PageRequest.of(0, pageSize))
					.forEach(System.out::println);

			//진행중, 진행완료 설정할 파라미터
			Long memberId = 1L; // 테스트할 멤버 ID
			Pageable pageable = PageRequest.of(0, 15); // 페이징 설정: 첫 페이지, 15개씩 가져오기

			// 진행중 미션 출력
			System.out.println("=== 진행중 미션 ===");
			Page<Mission> inProgressMissions = missionService.getInProgressMissions(memberId, pageable);
			inProgressMissions.forEach(mission -> System.out.println(mission));

			// 완료된 미션 출력
			System.out.println("=== 완료된 미션 ===");
			Page<Mission> completedMissions = missionService.getCompletedMissions(memberId, pageable);
			completedMissions.forEach(mission -> System.out.println(mission));*/
		};
	}}