package leveloper.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id") // foreign key = member_id
    private Member member;

    /*
    cascase 설명 :
    cascade가 없으면 order를 persist할때 orderitem도 같이 해줘야 하는데
    cascade를 설정해주면 order만 persist 하면 orderitem도 자동으로 같이 해준다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 : [ORDER, CANCEL]

    /*
     연관관계 편의 메소드 :
     member에 orders가 있고, order에 member가 있는데
     이들의 값을 변경할 때 하나하나 해주면 실수할 여지도 있고 귀찮으므로
     원자적으로 하나의 메소드로 묶어서 사용한다.
     member를 수정할때 order의 member도 함께 수정해버린다.
     */
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
