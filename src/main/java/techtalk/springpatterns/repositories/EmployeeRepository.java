package techtalk.springpatterns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import techtalk.springpatterns.models.Employee;

@RestResource(path = "employees", rel = "employee")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
