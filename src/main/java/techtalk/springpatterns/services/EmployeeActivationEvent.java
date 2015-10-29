package techtalk.springpatterns.services;

import org.springframework.context.ApplicationEvent;

import techtalk.springpatterns.models.AuditEventType;

public class EmployeeActivationEvent extends ApplicationEvent {

	private Long employeeId;
	private AuditEventType eventType;
	
	public EmployeeActivationEvent(Object source, long employeeId, AuditEventType eventType) {
		super(source);
		this.employeeId = employeeId;
		this.eventType = eventType;
	}

	public Long getEmployeeId() {
		return employeeId;
	}
	
	public AuditEventType getEventType() {
		return eventType;
	}

}
