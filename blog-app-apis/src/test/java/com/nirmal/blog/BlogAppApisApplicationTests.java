package com.nirmal.blog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nirmal.blog.repos.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(BlogAppApisApplicationTests.class);
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	public void testRepo() {
		logger.info("Method : testRepo starts");
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
		logger.info("Method : testRepo ends");
	}
	
}
