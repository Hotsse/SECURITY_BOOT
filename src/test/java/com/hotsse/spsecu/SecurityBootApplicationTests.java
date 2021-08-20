package com.hotsse.spsecu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityBootApplicationTests {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		
		String raw = "test1234@";
		
		String enc = passwordEncoder.encode(raw);
		System.out.println(enc);
	}

}
