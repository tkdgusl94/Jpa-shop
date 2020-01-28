package leveloper.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    /*
    * EnumType은 기본적으로 ORDINARY인데 이렇게 되면 enum 값을 새로 추가 했을 경우 망한다.
    * READY=1, COMP=2인 상태에서 XXX=2, COMP=3으로 변하게 되면 기존 COMP였던 것들은 그냥 다 XXX로 바뀌게 된다.
    * 따라서 EnumType은 STRING으로 해줘야 한다. 안그러면 장애 생긴다.
    * */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
