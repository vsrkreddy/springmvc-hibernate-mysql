package com.company.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	//setup pointcut decalartion
	
	@Pointcut("execution(* com.company.springdemo.controller.*.*(..))")
	private void forControllerPackage(){}
	
	@Pointcut("execution(* com.company.springdemo.service.*.*(..))")
	private void forServicePackage(){}
	
	@Pointcut("execution(* com.company.springdemo.dao.*.*(..))")
	private void forDAOPackage(){}
	
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow(){}
	
	//add before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint){
		
		
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> Calling @Before the method"+theMethod);

		// display arguments to methods
		
			//get the arguments
			Object[] args= theJoinPoint.getArgs();
			
			//loop thru and display args
			for(Object tempArg: args){
				myLogger.info("====> Argument: "+tempArg);
			}
	}
	
	
	//add AfterReturning advice
	@AfterReturning(pointcut= "forAppFlow()", returning= "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult){
		
		
		//display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> Calling @AfterReturning the method"+theMethod);

		// display data returned
		
			myLogger.info("===> result:" +theResult);
	}
}
