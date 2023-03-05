package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {

    /*
    * 1. MemMemberRepsitory를 만들어준다.
    * 2. 만들었던 save기능을 동작하는지 해보면 된다  @Test (org.junit.jupiter.api)
    *   - 생성하고 돌리면 그냥 실행이 됨
    * */

    MemberRepository repository = new MemoryMemberRepository();

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
}