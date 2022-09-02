package practice.spring.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.spring.hellospring.domain.Member;
import practice.spring.hellospring.repository.MemberRepository;
import practice.spring.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

   private final MemberRepository memberRepository;
    //Service에 Repository 삽입
    
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 1. 회원 가입 메서드 생성
     */
    public Long join(Member member){
        // 중복된 이름을 가진 회원은 가입 불가능 조건
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //회원 가입하면 아이디만 반환하도록 임의 설정
    }

   /* public Long join(Member member){
        // 중복된 이름을 가진 회원은 가입 불가능 조건
        long start = System.currentTimeMillis(); //로직 시작 시간 찍기
        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId(); //회원 가입하면 아이디만 반환하도록 임의 설정
        } finally {
            long finish = System.currentTimeMillis(); //로직 끝나는 시간 찍기
            long timeMs = finish - start; //로직 돌아가는데 걸린 시간 계산
            System.out.println("join = " + timeMs + "ms"); //메서드 명 + 걸린 시간 출력
        }

    }*/
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //result가 null이 아니라 값이 있으면 throw 이하가 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다."); //
                 });
    }

    /**
     * 2.전체 회원 조회
    **/
    public List<Member> findMembers(){

        return memberRepository.findAll(); //List형으로 반환
    }
    /*public List<Member> findMembers(){

        long start = System.currentTimeMillis(); //로직 시작 시간 찍기
        try {
            return memberRepository.findAll(); //List형으로 반환
        }finally {
            long finish = System.currentTimeMillis(); //로직 끝나는 시간 찍기
            long timeMs = finish - start; //로직 돌아가는데 걸린 시간 계산
            System.out.println("findMembers = " + timeMs + "ms"); //메서드 명 + 걸린 시간 출력
        }

    }*/

   /**
     * 3. 회원 찾기 메서드
     */
    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }


}
