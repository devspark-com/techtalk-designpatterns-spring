package techtalk.springpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TechtalkSpringPatternsApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(TechtalkSpringPatternsApplicationTest.class, args);
    }
}
