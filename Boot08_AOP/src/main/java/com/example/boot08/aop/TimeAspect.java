package com.example.boot08.aop;

import java.util.Date;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    /*
     *  spring이 관리하는 bean의 메서드가 수행되기 이전(Before)에 적용되는 Aspect
     *  [ 메서드 패턴 ]
     *  리턴 type => void
     *  메서드명 => write로 시작하는 메서드 
     *  메서드 매개변수 => 없음
     *  
     *  Aspect가 적용되는 위치를 "point cut"이라 부른다. 
     */
    @Before("execution(void write*())")
    public void start() {
        Date start = new Date();
        long startTime = start.getTime();
        System.out.println("시작 시간: " + startTime);
    }

    @After("execution(void write*())")
    public void end() {
        Date end = new Date();
        long endTime = end.getTime();
        System.out.println("종료 시간: " + endTime);
    }
}
