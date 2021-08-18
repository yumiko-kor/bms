package com.bms.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	@Before("execution(* com.bms.*.service.*.*(..)) or " + "execution(* com.bms.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {

		logger.info("-------------------------------------");
		logger.info("-------------------------------------");
		logger.info("1:" + Arrays.toString(jp.getArgs()));
		logger.info("2:" + jp.getKind());
		logger.info("3:" + jp.getSignature().getName());
		logger.info("4:" + jp.getTarget().toString());
		logger.info("5:" + jp.getThis().toString());

	}
	
	@After("execution(* com.bms.*.service.*.*(..)) or " + "execution(* com.bms.*.*.dao.*.*(..))")
	public void after(JoinPoint jp) {
		
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");
		logger.info("1:" + Arrays.toString(jp.getArgs()));
		logger.info("2:" + jp.getKind());
		logger.info("3:" + jp.getSignature().getName());
		logger.info("4:" + jp.getTarget().toString());
		logger.info("5:" + jp.getThis().toString());
	
	}


	@Around("execution(* com.bms.*.service.*.*(..)) or " + "execution(* com.bms.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		
		logger.info(Arrays.toString(pjp.getArgs()));

		Object result = pjp.proceed(); 

		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime)); 
		logger.info("==============================");

		return result;
		
	}
	

}
