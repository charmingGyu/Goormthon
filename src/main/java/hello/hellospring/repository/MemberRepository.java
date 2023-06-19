package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * @author hyoungmin.park
 */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional --> null 값을 null 값 그대로 받는것이 아닌 Optional 로 감싸서 받는다
    Optional<Member> findByName(String name);
    List<Member> findAll();
}