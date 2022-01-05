package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    /**
     * Category 내에서 하위 카테고리가 있는 것을 표현한 부분 (parent, child)
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 하나의 카테고리가 여러 아이템에 포함되고, 하나의 아이템이 여러 카테고리에 포함될 수 있다는 가정
     * But, 실무에서 ManyToMany 사용은 권장하지 않음. 다른 방법으로..
     */
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            /**
             * 중간 테이블인 CATEGORY_ITEM 테이블을 만드는 부분
            */
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();
}
