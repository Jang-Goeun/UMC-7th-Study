package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.ReviewImage;

import java.util.ArrayList;
import java.util.List;

import static umc.spring.domain.QRegion.region;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    private Float score;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> ReviewImageList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score='" + score + '\'' +
                ", member=" + member +
                ", store=" + store +
                '}';
    }
}