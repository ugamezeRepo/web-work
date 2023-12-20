package com.example.boot03;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot03ThymeleafApplication {

	public static void main(String[] args) {

		// 크롬을 실행해서 http://localhost:9000/boot05 로딩하기
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c start chrome.exe http://localhost:9000/boot03/");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SpringApplication.run(Boot03ThymeleafApplication.class, args);
	}

}
