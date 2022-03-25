package fr.af.offerpoc.commun;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Service in charge of supervision and log arround Service and Controller
 *
 * @Author TGI
 * @Date 10/03/2022
 */
@Aspect
@Component
@Slf4j
public class OfferLogAspect {

    @Pointcut("execution(public * fr.af.offerpoc.service.*Service.*(..))")
    public void serviceCall() {
    }

    @Pointcut("execution(public * fr.af.offerpoc.web.controller.*Controller.*(..))")
    public void controllerCall() {
    }


    /**
     * Log begining call of Services
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before("serviceCall()")
    public void logBeforeService(JoinPoint joinPoint) {
        log.info("SERVICE -START of Call of {} with {} paremeter",
                joinPoint.toShortString(),
                joinPoint.getArgs().length);
    }


    /**
     * Log supervision of Services
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("serviceCall()")
    public Object logArroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            log.info("SERVICE - END of Call of {} with {} paremeter in : {}ms",
                    joinPoint.toShortString(),
                    joinPoint.getArgs().length,
                    duree);
        }
    }


    /**
     * log begining call of controller
     *
     * @param joinPoint
     */
    @Before("controllerCall()")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("CONTROLLER -START of Call of {} with {} paremeter",
                joinPoint.toShortString(),
                joinPoint.getArgs().length);
    }

    /**
     * Log supervision of controller
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerCall()")
    public Object logArroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            log.info("CONTROLLER - END of Call of {} with {} paremeter in : {}ms",
                    joinPoint.toShortString(),
                    joinPoint.getArgs().length,
                    duree);
        }
    }


}
