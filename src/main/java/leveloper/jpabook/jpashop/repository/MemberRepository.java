package leveloper.jpabook.jpashop.repository;

import leveloper.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // 스프링이 생성한 엔티티매니저를 여기에 주입을 해준다.
    private final EntityManager em;

    /*
    persist하면 영속성 컨텍스트에 member 객체를 넣는다.
    나중에 트랜잭션이 커밋되는 시기에 디비에 넣는다.
     */
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /*
    아래의 쿼리는 sql이 아니라 JPQL이다.
    JPQL이랑 sql의 차이는 거의 같지만, table명 대신 entity명이 들어간다고 보면 된다.
    아래에서는 Member라는 entity명이 사용 되었다.
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
