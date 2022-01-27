package dojo.api.aop;

import dojo.api.service.IBusinessService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Fanmao.Li
 * @since 06/09/2019
 */
@Aspect
@Component
@Order(1)
public class AuthorizationAop {
    @Pointcut("execution(* dojo.api.service.IBusinessService+.*(..))")
    public void process(){}

    @Before("process()")
    public void beforeProcess(JoinPoint joinPoint) {
        System.out.println("Trace Id : " + IBusinessService.traceId.get() + " Before invoke in AuthorizationAop : " + joinPoint.getSignature());
    }

    @After("process()")
    public void afterProcess(JoinPoint joinPoint) {
        System.out.println("Trace Id : " + IBusinessService.traceId.get() + "After invoke in AuthorizationAop: " + joinPoint.getSignature());
        IBusinessService.traceId.remove();
    }
}
