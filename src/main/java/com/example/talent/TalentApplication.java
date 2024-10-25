package com.example.talent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TalentApplication {

	public static void main(String[] args) {

		// Cargar variables de entorno desde el archivo .env
		try (FileInputStream fis = new FileInputStream(".env")) {
			Properties prop = new Properties();
			prop.load(fis);

			// Configurar las variables de entorno en el sistema
			prop.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}


		SpringApplication.run(TalentApplication.class, args);
	}
}
