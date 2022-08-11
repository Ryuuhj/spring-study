package practice.spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.spring.hellospring.repository.MemberRepository;
import practice.spring.hellospring.repository.MemoryMemberRepository;
import practice.spring.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    //스프링이 뜰 때 컨테이너에 해당 빈 생성
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        //아래 생성한 memberRepsoitory를 인자로 넣음
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        //인터페이스므로 new로 구현 시 구현체를 이용
    }
}
