package techtalk.springpatterns.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import techtalk.springpatterns.models.Employee;
import techtalk.springpatterns.repositories.EmployeeRepository;

@Component
public class EmployeeWriter implements ItemWriter<Employee> {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		employeeRepository.save(employees);
	}
}
