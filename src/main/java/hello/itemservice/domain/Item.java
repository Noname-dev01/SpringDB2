package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Entity JPA가 사용하는 객체라는 뜻, 이 에노테이션이 있어야 JPA가 인식 할 수 있다.
 * @Id 테이블의 PK와 해당 필드를 매핑한다.
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * PK 생성 값을 데이터베이스에서 생성하는 IDENTITY 방식을 사용한다.
 * @Column 객체의 필드를 테이블의 컬럼과 매핑한다.
 * 필드이름과 테이블 컬럼이름이 같으면 생략 해도 된다.
 * 아래 itemName 컬럼은 생략해도 되지만 예시로 해주었다.
 */
@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    /**
     * JPA는 public 또는 protected의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어주자.
     */
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
