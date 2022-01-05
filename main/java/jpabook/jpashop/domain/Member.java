package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    // 좋은 방식은 아니다.(회원이 주문에 리스트까지 갖고 있을 필요는 없음. 주문 관련은 Order 부터 시작하면 됨. 끊어 내기가 중요)
    // 하지만 여기선 OneToMany 연관관계 매핑 연습을 위해 해보자.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
