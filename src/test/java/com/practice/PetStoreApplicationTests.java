package com.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetStoreApplicationTests {

	@Test
	void contextLoads() {
		int i = 1;
		assertEquals(1,i);
	}

}
