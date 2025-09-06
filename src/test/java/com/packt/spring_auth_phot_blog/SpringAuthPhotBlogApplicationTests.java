package com.packt.spring_auth_phot_blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringAuthPhotBlogApplicationTests {

	@Test
	void contextLoads() {
	}

}
