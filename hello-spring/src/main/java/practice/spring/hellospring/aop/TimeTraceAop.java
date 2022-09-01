package practice.spring.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //구현 로직은 전과 동일
        long start = System.currentTimeMillis();
        //어떤 메서드를 call 하는지 확인하기
        System.out.println("START: " + joinPoint.toString());
        try {
            //proceed = 다음 메서드로 진행시킴
            return joinPoint.proceed();

        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            //메서드 이름 +
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
