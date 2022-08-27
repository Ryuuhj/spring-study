package practice.spring.hellospring;

import practice.spring.hellospring.repository.JdbcMemberRepository;
//import practice.spring.hellospring.repository.JdbcTemplateMemberRepository;
import practice.spring.hellospring.repository.MemberRepository;
import practice.spring.hellospring.repository.MemoryMemberRepository;
import practice.spring.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
