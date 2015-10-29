package techtalk.springpatterns.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import techtalk.springpatterns.models.AuditEvent;
import techtalk.springpatterns.models.AuditEventType;
import techtalk.springpatterns.models.Employee;
import techtalk.springpatterns.repositories.AuditEventRepository;
import techtalk.springpatterns.repositories.EmployeeRepository;

/**
 * Composite test
 * 
 * @author javier
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AuditEventRepository auditEventRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	public void activate(Long employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		Assert.notNull(employee, "Employee");

		if (employee.isActive()) {
			throw new IllegalStateException("Employee already active");
		}

		employee.setActive(true);
		employeeRepository.save(employee);

		auditEventRepository.save(buildAuditEvent(employee, true));
		
		publishEvent(employeeId, true);
	}

	public void deactivate(Long employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		Assert.notNull(employee, "Employee");

		if (!employee.isActive()) {
			throw new IllegalStateException("Employee not active");
		}

		employee.setActive(false);
		employeeRepository.save(employee);

		auditEventRepository.save(buildAuditEvent(employee, false));
		
		publishEvent(employeeId, false);

	}

	private AuditEvent buildAuditEvent(Employee employee, boolean active) {
		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setEmployee(employee);
		auditEvent.setUpdatedOn(new Date());
		auditEvent.setEventType(active ? AuditEventType.ACTIVATE
				: AuditEventType.DEACTIVATE);

		return auditEvent;
	}

	private void publishEvent(long employeeId, boolean active) {
		publisher.publishEvent(new EmployeeActivationEvent(this, employeeId,
				active ? AuditEventType.ACTIVATE : AuditEventType.DEACTIVATE));
	}
	
}
