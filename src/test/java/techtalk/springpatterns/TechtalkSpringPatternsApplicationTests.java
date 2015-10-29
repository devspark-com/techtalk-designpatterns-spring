package techtalk.springpatterns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import techtalk.springpatterns.models.Employee;
import techtalk.springpatterns.repositories.EmployeeRepository;
import techtalk.springpatterns.services.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TechtalkSpringPatternsApplicationTest.class)
public class TechtalkSpringPatternsApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void contextLoads() {
		Employee employee = new Employee();
		employee.setActive(false);
		employee.setAge(99);
		employee.setName("Cosme Fulanito");
		
		employeeRepository.save(employee);
		
		employeeService.activate(employee.getId());
	}

}
