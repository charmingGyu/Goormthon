package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author hyoungmin.park
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // interface 가 interface 를 받을때는 extends (implements X)

    // JPQL select m from Member m where m.name = ? --> jpql 로 짜주면 SQL로 번역을 해줘서 실행
    @Override
    Optional<Member> findByName(String name); // 구현할게 없다.

}