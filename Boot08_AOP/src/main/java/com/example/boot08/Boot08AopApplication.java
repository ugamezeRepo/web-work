package com.example.boot08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.boot08.util.Messenger;
import com.example.boot08.util.WritingUtil;

@SpringBootApplication
public class Boot08AopApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Boot08AopApplication.class, args);

        // spring이 관리하는 bean들 중에서 WritingUtil type 객체의 참조값 얻어오기
        WritingUtil util = ctx.getBean(WritingUtil.class);

        util.writeLetter();
        util.writeReport();
        util.writeDiary();

        Messenger messenger = ctx.getBean(Messenger.class);
        messenger.sendGreeting("안녕하세요~");
        messenger.sendGreeting("똥깨야 안녕!");
    }

}
