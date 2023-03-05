package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /*
    *테스트는 과감하게 한글로 바꿔도됨
    * 1. MemberService가 있어야한다.
    * 2. 회원가입코드를 만든다 join()
    *   - given, when, then문법으로 주어져서 이것을 실행했을떄 결과가 이게 나와야대
    *   - when: 멤버 서비스의 join을 검증하는것으로 member객체를 넣는다. saveId로$
    *   - then: Assertions로 assertThat()으로 ,
    *       - 리포지토리를 꺼내야하는데 .... memberService.findOne(saveId)로 one으로 빼서
    * */
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get(); //Optional꺼라서 .get();으로 꺼냄
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //중복_회원_예외 member1, member2로 넣어서 예외를 터지게하기
    @Test
    public void 중복_회원_예외 () {
        Member member1 = new Member();
        member1.setName("Jess");

        Member member2 = new Member();
        member2.setName("Jess");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다ㄴㄴ.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}