package techtalk.springpatterns.aspects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Proxy test
 * 
 * @author javier
 *
 */
@Component
@Aspect
public class LoggerAspect {

	private final Log log = LogFactory.getLog(getClass());

	@AfterReturning("execution(* techtalk.springpatterns.services.*.*(..))")
	public void logMethodAccessAfter(JoinPoint joinPoint) {
		log.info("***** Completed: " + joinPoint.getSignature().getName()
				+ " *****");
	}

	@Before("execution(* techtalk.springpatterns.services.*.*(..))")
	public void logMethodAccessBefore(JoinPoint joinPoint) {
		log.info("***** Starting: " + joinPoint.getSignature().getName()
				+ " *****");
	}
}
