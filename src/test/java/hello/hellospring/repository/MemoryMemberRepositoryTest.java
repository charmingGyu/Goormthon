package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * @author hyoungmin.park
 */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 실행일 끝날때마다 어떤 동작을 하는 것, 콜백 메서드
    public void afterEach() { // 원래 테스트는 순서가 보장이 안돼서 정상적인 코드도 에러가 날 수 있음( ex.기존에 저장되어있는 메서드가 저장된 채로 다른 메서드에 들어가게 될 경우)
        repository.clearStore(); // 순서 상관 없어짐

    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // null 값 넣으면 에러뜬다., Assertions.assertThat
        // Assertions.assertEquals(member, result); // null값 넣으면 에러
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // Shift +F6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //commend + option + V
// spring2로 하면 에러발생
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

//    @Test
//    public void test() {
//        List<Integer> list = new ArrayList<>();
//
//        list.add(1);
//        list.add(7);
//        list.add(5);
//        list.add(2);
//        list.add(3);
//        list.add(6);
//        list.add(4);
//        list.add(4);
//        list.add(4);
//        list.add(10);
//        list.add(8);
//        list.add(9);
//        list.add(9);
//        list.add(9);
//
//
//        List<Integer> list1 = list.stream()
//                .distinct()
//                .collect(Collectors.toList());
//
//        list1.stream()
//                .forEach(element -> System.out.println(element));
//
//    }
//}