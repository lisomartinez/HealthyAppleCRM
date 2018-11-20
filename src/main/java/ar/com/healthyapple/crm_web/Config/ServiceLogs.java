package ar.com.healthyapple.crm_web.Config;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Aspect
public class ServiceLogs {

//    @Pointcut("@within(org.springframework.stereotype.QuoteItemDto)")
//    public void allResources() {
//        // don't need code
//    }
//
//    @Before("allResources()")
//    public void callToService(JoinPoint jp) {
//        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------------- ***** ( -- SERVICE -- ) ***** ---------------------");
//        StringBuilder log = new StringBuilder(jp.getSignature().getType() + " >>>>----->>>");
//        for (Object arg : jp.getArgs()) {
//            log.append("\n   --->>> PARAM: " + arg);
//        }
//        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
//    }
//
//    @AfterReturning(pointcut = "allResources()", returning = "result")
//    public void apiResponseLog(JoinPoint jp, Object result) {
//        String log = "<<<<----<<<< ((( -- Return -- ))) <<<< " + jp.getSignature().getType() + ": " + result;
//        if (log.length() > 2000) {
//            log = log.substring(0, 2000) + ".... (+" + log.length() + " characters)";
//        }
//        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
//    }
}
