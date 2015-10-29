package techtalk.springpatterns;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ResourceLoader;

import techtalk.springpatterns.batch.EmployeeLineMapper;
import techtalk.springpatterns.models.Employee;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableBatchProcessing
public class TechtalkSpringPatternsApplication {

	@Autowired
	private ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(TechtalkSpringPatternsApplication.class, args);
	}

	@Bean
	public ItemStreamReader<Employee> employeeCSVFileReader() {

		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<Employee>();
		lineMapper.setFieldSetMapper(new EmployeeLineMapper());

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "name", "age" });
		lineMapper.setLineTokenizer(lineTokenizer);

		FlatFileItemReader<Employee> csvReader = new FlatFileItemReader<Employee>();
		csvReader.setLinesToSkip(1);
		csvReader.setLineMapper(lineMapper);

		String url = "classpath:employees.csv";
		csvReader.setResource(resourceLoader.getResource(url));

		return csvReader;
	}

	@Bean
	public Step dbPopulationStep(StepBuilderFactory stepBuilderFactory,
			ItemStreamReader<Employee> employeeCSVFileReader,
			ItemProcessor<Employee, Employee> employeeProcessor,
			ItemWriter<Employee> employeeWriter) {
		return stepBuilderFactory.get("employeeProcessorStep")
				.<Employee, Employee> chunk(10)
				.reader(employeeCSVFileReader)
				.processor(employeeProcessor)
				.writer(employeeWriter)
				.throttleLimit(1)
				.build();
	}

	@Bean
	public Job dbPopulation(JobBuilderFactory jobs, Step dbPopulationStep) {
		return jobs.get("employeeProcss")
				.incrementer(new RunIdIncrementer())
				.flow(dbPopulationStep)
				.end()
				.build();
	}

}
