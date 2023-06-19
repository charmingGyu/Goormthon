package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author hyoungmin.park
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 같은 키값을 생성해주는 것

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 컴퓨터가 정해주는 id를 세팅하고
        store.put(member.getId(), member); // store 에 저장 -->Map 에 저장된다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 찾으면 반환하는 것 , 하나라도 찾는것 루프를 돌았는데 없으면 Optional 에 포함되어서 반환

    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // List 가 루프돌리기 편해서 많이 사용, store 에 있는 member 를 반환
    }

    public void clearStore() {
        store.clear();
    }
}