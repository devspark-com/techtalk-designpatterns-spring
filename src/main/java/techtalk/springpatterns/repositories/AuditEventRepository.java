package techtalk.springpatterns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import techtalk.springpatterns.models.AuditEvent;

@RestResource(path = "auditevents", rel = "audit_event")
public interface AuditEventRepository extends CrudRepository<AuditEvent, Long> {
	
}
