package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hyoungmin.park
 */
class MemberServiceTest { //commend + shift + T -> test 자동으로 생성

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public  void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }
    private static Map<Long, Member> store = new HashMap<>();
    @AfterEach
    public void afterEach() {
        memberService.clearStore();
    }

    @Test
    void 회원가입() {
        //given(어떤 상황이 주어져서)
        Member member = new Member();
        member.setName("hello");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
    public void clearStore() {
        store.clear();
        }

}