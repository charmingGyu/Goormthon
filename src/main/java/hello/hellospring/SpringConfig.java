package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hyoungmin.park
 */
@Configuration
public class SpringConfig {

    // @Autowired DataSource dataSource;

    //    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {this.dataSource = dataSource;}

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    private final MemberRepository memberRepository;

    @Autowired // 생성자가 하나일 경우 생략 가능
  public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // spring bean 등록
    public MemberService memberService() {
        return new MemberService(memberRepository);
        // memberService(), memberRepository() Spring bean 에 등록을 하고
        // Spring bean 에 등록되어있는 memberRepository 를 memberService 에 넣어준다.
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
////    }
//
}