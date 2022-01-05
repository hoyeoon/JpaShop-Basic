package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address {

    // validation 타입을 공통으로 쓰기 때문에 의미있다.
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    private void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /** equals() and hashCode() 생성할 때
     * Use getters during code generation 옵션을 체크하여 필드 대신 getter 로 접근하는 것이 좋다.
     * 왜냐하면 필드로 직접 접근하면 proxy 일 때 계산이 안된다.
     * 즉, getter 를 호출해야 객체가 proxy 일 때에도 proxy 객체가 진짜 객체에게 가는 것이 가능하다.
     * JPA 에서는 proxy 때문에 이것을 고려하여 항상 getter 를 활용하여 구현하는 것이 좋다.
      */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
