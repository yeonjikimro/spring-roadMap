package hello.hellospring;

import hello.hellospring.Service.MemberService;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig { // @Service, @Repository 대신 사용


    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // 이 bean 대신 TimeTraceAop 클래스에 @Componenet 어노테이션을 달아도 된다.
/*    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

/*    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JpaMemberRepository(em);
        return
    }*/




}
