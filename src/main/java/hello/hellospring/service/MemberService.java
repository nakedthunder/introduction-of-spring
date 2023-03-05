package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    /*
    * 1. 회원 서비스를 만들라면 리포지토리가 있어야한다. MemberRepository를해준다.
    * 2. 회원가입 join()
    *   - 회원가입은 save만 호출해준다. return은 id만 반환
    *   - 회원가입을 할때 비지니스 로직에 중복되는 이름은 안된다.
    *   - commnad + option + V 하면
    *   - ifPresent()에 값이 있으면 이미 존재하는 회원 엑셉션을 띄어줌 (옵셔널이니깐 가능)
    *       - 옵셔널로 감싸면 옵셔널안에 멤버객체가 있어서 감싸서 쓸 수 있는 것
    *       - 감싼 덕분에 ifPresent가 있다. orElseGet()도 꺼냄
    *   - 옵셔널을 바로 꺼내는걸 권하지않음 -> 어짜피
    * */

    // 같은 인스턴스를 쓰기위해서 new를 지움
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); //회원가입
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 값이 있습니다.");
                });
    }

    /*
    * 전체 회원 조회하는 기능
    *   리포지토리는 저장소에 넣다뺴는 개념이라면
    *   서비스 클래스는 비즈니스용 사이드이다. , 리포지토리는 단순 데이터를 넣다 빼는 것
    * findAll();로 반환은 List<Member>
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
