package techtalk.springpatterns.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import techtalk.springpatterns.models.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
	@Override
	public Employee process(Employee employee) throws Exception {
		employee.setActive(false);
		return employee;
	}
}
