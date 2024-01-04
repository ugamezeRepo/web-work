package com.example.boot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.boot07.dto.MemberDto;

@SpringBootApplication
public class Boot07FileuploadApplication {

    public static void main(String[] args) {
        SpringApplication
                .run(Boot07FileuploadApplication.class, args);

        // 크롬을 실행해서 http://localhost:9000/boot07 로딩하기
        // Runtime rt = Runtime.getRuntime();
        // try {
        // rt.exec("cmd /c start chrome.exe http://localhost:9000/boot07");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        MemberDto dto = new MemberDto();
        dto
                .setNum(1);
        dto
                .setName("김구라");
        dto
                .setAddr("노량진");
        System.out
                .println(dto);
        MemberDto dto2 = MemberDto
                .builder()
                .num(2)
                .name("해골")
                .addr("행신동")
                .build();
        System.out
                .println(dto2);
    }

}
