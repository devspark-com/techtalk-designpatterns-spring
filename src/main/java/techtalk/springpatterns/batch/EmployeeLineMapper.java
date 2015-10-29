package techtalk.springpatterns.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import techtalk.springpatterns.models.Employee;

public class EmployeeLineMapper implements FieldSetMapper<Employee> {
	@Override
	public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
		Employee employee = new Employee();
		employee.setAge(fieldSet.readInt("age"));
		employee.setName(fieldSet.readString("name"));
		
		return employee;
	}
}
