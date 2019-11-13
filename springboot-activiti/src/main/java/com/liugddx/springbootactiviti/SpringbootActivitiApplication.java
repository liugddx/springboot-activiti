package com.liugddx.springbootactiviti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ImportResource({
  "provider.xml"
})
public class SpringbootActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActivitiApplication.class, args);
	}

}
