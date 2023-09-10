package echo.com.community_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "echo.com.community_test.mapper")
@SpringBootApplication()
public class CommunityTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityTestApplication.class, args);
    }

}
