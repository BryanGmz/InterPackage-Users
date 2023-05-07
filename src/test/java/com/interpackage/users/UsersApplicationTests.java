package com.interpackage.users;

import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@EnableWebSecurity
class UsersApplicationTests extends AbstractIntegrationTest{


	@Test
	void contextLoads() {
	}

}
