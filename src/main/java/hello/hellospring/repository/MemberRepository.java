package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    /*
    * - save: 회원을 저장하면 저장된 회원을 반환 (회원이 저장소에 저장)
    * - findById/findByName: 아이디/이름으로 찾음
    * - Optional: findById 가져왔는데 없으면 null로 반환되는데 null로 바로 내리지 않고
    *  Optional로 감싸서 반환 (Java8)
    * */
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
