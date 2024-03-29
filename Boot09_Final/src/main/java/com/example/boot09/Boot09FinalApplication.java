package com.example.boot09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import com.example.boot09.dto.GalleryDto;

@PropertySource(value = "classpath:custom.properties")
@SpringBootApplication
public class Boot09FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot09FinalApplication.class, args);
		
		GalleryDto dto = new GalleryDto();
		
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
		    System.out.println("OS : Windows");
		    
		} else if (os.contains("mac")) {
		    System.out.println("OS : Mac");
		}
	}

}
