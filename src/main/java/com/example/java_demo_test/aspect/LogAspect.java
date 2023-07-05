package com.example.java_demo_test.aspect;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

	@Pointcut("execution (public * com.example.java_demo_test.service.impl.*.*(..))")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void befor() {
		logger.info("=====before device=====");
	}
	
	@Before("execution (public * com.example.java_demo_test.controller.*.*(..)) && args(requestObj, ..)")
	public void controllerBefore(Object requestObj) {
		// 排出特定方法
		// ServletRequestAttributes 裡面才有getAttributes()，RequestAttribute裡面沒有，所以才需要轉型
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String requestUri = Objects.requireNonNull(attributes).getRequest().getRequestURI();
		if (requestUri == "") {
			// 排除該方法
		}
	}

	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===== around before =====");
		// 呼叫proceed()才會執行原方法
		Object res = pjp.proceed();
		System.out.println("===== around after =====");
		return res;
	}

	// throwing後面字串名要跟方法參數一樣
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void afterThrowing(Throwable throwable) {
		// 獲得錯誤訊息
		System.out.println("錯誤訊息:" + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
