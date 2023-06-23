package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author hyoungmin.park
 */
@Configuration
public class SpringConfig {

    // @Autowired DataSource dataSource;

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // spring bean 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
        // memberService(), memberRepository() Spring bean 에 등록을 하고
        // Spring bean 에 등록되어있는 memberRepository 를 memberService 에 넣어준다.
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

}