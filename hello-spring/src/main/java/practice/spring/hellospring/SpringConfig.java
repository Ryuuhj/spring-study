package practice.spring.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import practice.spring.hellospring.aop.TimeTraceAop;
import practice.spring.hellospring.repository.*;
//import practice.spring.hellospring.repository.JdbcTemplateMemberRepository;
import practice.spring.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {



    /*private final DataSource dataSource;
        private EntityManager em;
        @Autowired
        public SpringConfig(DataSource dataSource, EntityManager em) {
            this.dataSource = dataSource;
            this.em = em;
        }*/
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }*/
    /*@Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }

    //TimeTraceAop 스프링 빈으로 직접 등록

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
