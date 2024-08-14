package com.keyboardsba.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // 공통 사항에 대해서 일을 하는것, 부가 기능 정의(Advice) + 어디에 적용할건지에(PointCut) 대한 합친 부분. = (pointcut)
@Component
public class TimeTraceAop {
	
	// @Around("execution(* com.keyboardsba..*(..))") // 패키지 더미 => 어디에 적용을 할것인지? execution-> 정해진 그 밑에 모든것들에 대해서 정리.
	@Around("@annotation(TimeTrace)") // 모두다 소문자로 지정을 해야한다. 어느 어노테이션이 붙어 있을 때 수행을 할 것인가?
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 시간 측정
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object proceedObj =  joinPoint.proceed(); // 회원가입일수도있고 로그인일수도있고.. 본래 할 일을 수행
		
		sw.stop();
		
		log.info("$$$$$$$$$$ 실행 시간(ms):" + sw.getTotalTimeMillis());
		log.info(sw.prettyPrint());
		
		return proceedObj;
	}
}
