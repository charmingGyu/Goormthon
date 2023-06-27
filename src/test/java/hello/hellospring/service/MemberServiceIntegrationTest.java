package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author hyoungmin.park
 */
@SpringBootTest
@Transactional
//test case 에 달면 test를 실행할 때 Transactional 을 먼저 실행하고 DB에 data를
//다 넣은 다음에 test가 끝나면 rollback해준다 따라서 DB에 넣었던 데이터를 반영을 안하고 다 지워진다.
//test case 에 넣었을 때만 롤백! 서비스에 붙으면 롤백하지 않고 정상적으로 작동
class MemberServiceIntegrationTest { //commend + shift + T -> test 자동으로 생성


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    private static Map<Long, Member> store = new HashMap<>();
//
//    @AfterEach
//    public void afterEach() {
//        memberService.clearStore();
//    }

    @Test
    void 회원가입() {
        //given(어떤 상황이 주어져서)
        Member member = new Member();
        member.setName("spring100");

        //when(실행했을 때)
        Long saveId = memberService.join(member);

        //then(결과는?)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // option + enter
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // memberService.join(member2); 중복에서 걸린다.
//        try {
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
//
//    public void clearStore() {
//        store.clear();
//    }

}