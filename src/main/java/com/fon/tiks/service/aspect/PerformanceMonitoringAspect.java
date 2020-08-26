package com.fon.tiks.service.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

import com.fon.tiks.annotation.Monitoring;

@Aspect
@Component
public class PerformanceMonitoringAspect {
	
	private static final Logger logger = LoggerFactory.getLogger("MONITORING");
	
	private final Runtime runtime = Runtime.getRuntime();
	
	@Pointcut("execution(public * com.fon..*.*(..))")
	public void publicMethod() {
		
	}
	
	@Around("publicMethod() && target(target) && @annotation(monitoring)")
	public Object logAround(ProceedingJoinPoint joinPoint, Object target, Monitoring monitoring) throws Throwable {
		Map<String, Object> logMap = createLogMap(joinPoint, target, monitoring);

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		logMap.put("DURATION", System.currentTimeMillis() - start);
		logger.info(MarkerFactory.getMarker("JSON"), "message", logMap);
		
		return proceed;
	}

	private Map<String, Object> createLogMap(ProceedingJoinPoint joinPoint, Object target, Monitoring monitoring) {
		Map<String, Object> logMap = new HashMap<>();
		logMap.put("METHOD", joinPoint.getSignature().getName());
		logMap.put("CLASS", target.getClass());
		logMap.put("MEMORY", getRuntimeInfo());
		logMap.put("DATABASE", monitoring.database());
		logMap.put("DATA SIZE", getDataSize(monitoring));
		logMap.put("OPERATION", monitoring.operation());
		return logMap;
	}
	
	private Map<String, Object> getRuntimeInfo() {
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		
		Map<String, Object> runtimeInfo = new HashMap<>();
		runtimeInfo.put("TOTAL_MEMORY", totalMemory);
		runtimeInfo.put("FREE_MEMORY", freeMemory);
		runtimeInfo.put("USED_MEMORY", totalMemory - freeMemory);
		return runtimeInfo;
	}
	
	private String getDataSize(Monitoring monitoring) {
		String dataSize = "";
		switch (monitoring.dataSize()) {
		case SMALL:
			dataSize = "1000";
			break;
		case MEDIUM:
			dataSize = "30000";
			break;
		case LARGE:
			dataSize = "100000";
			break;
		default:
			break;
		}
		return dataSize;
	}
	
}
