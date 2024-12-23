package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @OneToMany(mappedBy = "preferCategory", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();
}