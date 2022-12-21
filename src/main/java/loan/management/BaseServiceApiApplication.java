package loan.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@Slf4j
@SpringBootApplication
@EntityScan(basePackageClasses = {
		BaseServiceApiApplication.class,
		Jsr310JpaConverters.class
})
public class BaseServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseServiceApiApplication.class, args);
	}

}
