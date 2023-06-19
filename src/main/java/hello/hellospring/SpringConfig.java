package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hyoungmin.park
 */
@Configuration
public class SpringConfig {

    @Bean // spring bean 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
        // memberService(), memberRepository() Spring bean 에 등록을 하고
        // Spring bean 에 등록되어있는 memberRepository 를 memberService 에 넣어준다.
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}