package practice.spring.hellospring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.spring.hellospring.domain.Member;
import practice.spring.hellospring.repository.MemberRepository;
import practice.spring.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    /**
     * 테스트를 위한 MemberService 생성
     */
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /**
     * 0. 각 테스트 후에 repository를 초기화 해주는 메서드 정의 (각 테스트 끝날 때마다 실행)
     */
    //테스트 실행할때마다 각각 생성해줌 (독립적으로 실행돼야 하므로)
    // -> 스프링 컨테이너로부터 MemberService, MemoryMemberRepository 객체 얻어와야함 (자체 생성X) >> @Autowired로


    /**
     * 1. 회원가입 (join) 메서드 테스트
     */
    @Test
    void 회원가입() {
       //given
        Member member = new Member();
        member.setName("hello");

       //when
        Long saveId = memberService.join(member); //기존 join의 return값 == id

        //then
        //저장한 회원이 repository에 존재하는게 맞는지 테스트
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //비교 대상(findName)과 테스트 데이터(member)를 비교
    }
    //*** 중복 금지 기능 정상작동 확인 위해 중복회원 저장해보기 ***
    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //이름을 동일하게 (중복회원 생성)
        //when
        memberService.join(member1);
        //2번째 join시 exception 발생 예상

        //then
        //1. try-catch 문 사용
/*        try{
            memberService.join(member2);
            fail(); //Exception이 제대로 발생 안될경우 실행될 코드
        }catch (IllegalStateException e){ //성공할 경우 catch 동작
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //2. assertThrows 이용 - 람다 이하 로직을 수행할 때 exception이 발생해야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}