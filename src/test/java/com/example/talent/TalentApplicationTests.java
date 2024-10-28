package com.example.talent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootTest
class TalentApplicationTests {

	@BeforeAll
	public static void setup() {
		try (FileInputStream fis = new FileInputStream(".env")) {
			Properties prop = new Properties();
			prop.load(fis);
			prop.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void contextLoads() {
	}

}
