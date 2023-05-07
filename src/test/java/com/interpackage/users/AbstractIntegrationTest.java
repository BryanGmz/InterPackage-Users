package com.interpackage.users;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(properties = {
		"eureka.client.enabled=false",
		"jwt.secret=Z968CJlXkSXsKBBEYPdyuSTkbHFeGP+dAzDExoNq6Gw=",
		"spring.kafka.topic.name=user_topics",
		"spring.kafka.producer.bootstrap-servers=non-existent-server:9092"
})
public class AbstractIntegrationTest {

	@Container
	public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12");

	@Container
	GenericContainer<?> redis = new GenericContainer<>("redis:5.0.3-alpine")
			.withExposedPorts(6379);

	@Container
	public static KafkaContainer kafkaC = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);

	}

	@DynamicPropertySource
	static void kafkaProperties(DynamicPropertyRegistry registry) {
		kafkaC.start();
		registry.add("spring.kafka.properties.bootstrap.servers", kafkaC::getBootstrapServers);
		registry.add("spring.kafka.consumer.properties.auto.offset.reset", () -> "earliest");

	}

}
