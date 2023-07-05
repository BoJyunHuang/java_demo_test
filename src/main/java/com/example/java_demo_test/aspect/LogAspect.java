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
		// �ƥX�S�w��k
		// ServletRequestAttributes �̭��~��getAttributes()�ARequestAttribute�̭��S���A�ҥH�~�ݭn�૬
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String requestUri = Objects.requireNonNull(attributes).getRequest().getRequestURI();
		if (requestUri == "") {
			// �ư��Ӥ�k
		}
	}

	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===== around before =====");
		// �I�sproceed()�~�|������k
		Object res = pjp.proceed();
		System.out.println("===== around after =====");
		return res;
	}

	// throwing�᭱�r��W�n���k�ѼƤ@��
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void afterThrowing(Throwable throwable) {
		// ��o���~�T��
		System.out.println("���~�T��:" + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
