package techtalk.springpatterns.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Observer
 * 
 * @author javier
 *
 */
@Component
public class EmployeeActivationListener implements
		ApplicationListener<EmployeeActivationEvent> {

	private final Log log = LogFactory.getLog(getClass());

	public void onApplicationEvent(EmployeeActivationEvent event) {
		log.info("Event received [Employee id: " + event.getEmployeeId()
				+ "] [Event type: " + event.getEventType() + "]");
	}
}
