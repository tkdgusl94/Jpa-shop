package leveloper.jpabook.jpashop.service;

import leveloper.jpabook.jpashop.domain.Member;
import leveloper.jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // 스프링이랑 함께 테스트를 할래
@SpringBootTest // 스프링부트를 띄운 상태로 테스트 하려면 필요하다. 이게 없으면 Autowired가 실패한다.
@Transactional // 영속성 컨텍스트가 플러쉬를 안해버린다. 테스트가 끝나면 트랜잭션을 전부 다 롤백해버린다.
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) // 이렇게 하면 insert문을 날리는 것을 확인할 수 있다.
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush(); //이렇게 하면 강제로 디비에 insert 할 수 있다.
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외처리() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 한다.

        //then
        Assert.fail("예외가 발생해야 한다.");
    }
}