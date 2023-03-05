package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {

    /*
    * 1. MemMemberRepsitory를 만들어준다.
    * 2. 만들었던 save기능을 동작하는지 해보면 된다  @Test (org.junit.jupiter.api)
    *   - 생성하고 돌리면 그냥 실행이 됨
    * */

    //MemoryMemberRepository테스트 하는거다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        //MemoryMemberRepository 클래스에서 만든 store.clear()를 호출해주는 것
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        //반환 타입이 Optional이다. get으로 뽑을 수 있으니 (테스트에서만 get()사용)
        Member result = repository.findById(member.getId()).get();

        //new에서 한거랑 DB에서 한거랑 똑같으면 true이다
        //Assertions.assertEquals(member, result); //기대하는것, 결과

        //member가 result랑 동일하다. (Assertions > static import로 해주기)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("JESS");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("JAE");
        repository.save(member2); // JESS, JAE회원이 저장된 상황

        //get()으로 받아내서 reulst로 받아봄
        Member result = repository.findByName("JESS").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("JESS");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("JAE");
        repository.save(member2);

        List<Member> result = repository.findAll();
        //결과값 size()로 검사하기
        assertThat(result.size()).isEqualTo(2);
    }
}