package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author hyoungmin.park
 */
@Aspect // aop 사용하려면 필수!
@Component // SpringConfig 에 @Bean 으로 등록 해도됨!
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    // 예 : service 만 하고 싶으면 -->  @Around("execution(* hello.hellospring.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());// 이 안에 어떤 메서드를 콜하는지 이름을 다 알 수 있음.
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}