package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author hyoungmin.park
 */
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 EntityManager 로 모든게 동작을 한다.
// jpa를 사용하려면 EntityManager 를 주입받아야한다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist : 영속화하다, 영구저장하다.
        return member;
        // jpa가  insert query 다 만들어서 DB에 다 집어넣고 id까지 member, setId까지 다 해준다.
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { //findByName 은 jpql 이라는 객체지향쿼리라는 것을 사용해야함
        List<Member> result = em.createQuery("select m from Member m where m.name = :name ", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
//    List<Member> result = em.createQuery("select m from Member m", Member.class)
//            .getResultList();
//        return result;
//(^T-> inline 찾기) commend option N해서 inline 으로 합친 것 -->}
        return em.createQuery("select m from Member m", Member.class) // select m from Member m : jpql이라는 쿼리 언어
                .getResultList(); // table 을 대상으로 sql을 날리는데  객체를 대상으로 쿼리를 날리는 것
    }
}