package com.example.boot08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 *  - Aspectj Expression
 * 
 *  1. execution(* *(..)) => 접근 가능한 모든 메소드가 
 *     point cut
 *  2. execution(* test.service.*.*(..)) 
 *      => test.service 패키지의 모든 메소드 point cut
 *  3. execution(void insert*(..))
 *      =>리턴 type 은 void 이고 메소드명이 
 *      insert 로 시작하는 모든 메소드가 point cut
 *  4. execution(* delete*(*))
 *      => 메소드 명이 delete 로 시작하고 인자로 1개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 *  5. execution(* delete*(*,*))
 *      => 메소드 명이 delete 로 시작하고 인자로 2개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 *  6. execution(String update*(Integer,*))
 *      => 메소드 명이 update 로 시작하고 리턴 type 은 String
 *      메소드의 첫번째 인자는 Integer type, 두번째 인자는 아무 type 다되는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 */

@Aspect
@Component
public class MessengerAspect {

    @Around("execution(void send*(..))")
    public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드에 전달된 인자들 목록 얻어내기
        Object[] args = joinPoint.getArgs();

        // 반복문 돌면서 매개변수에 담긴 값들을 하나하나 조사해볼 수 있다.
        for (Object tmp : args) {
            // 원하는 type을 찾는다.
            if (tmp instanceof String) { // 만일 찾는 type(String)이면
                String msg = (String) tmp; // 원래 type으로 casting해서 작업한다.
                System.out.println("Aspect에서 읽어낸 내용: " + msg);

                if (msg.contains("똥깨")) {
                    System.out.println("똥깨는 금지된 단어입니다.");
                    return; // 메서드 끝내기
                }
            }
        }

        // 이 메서드를 호출하는 시점에 실제로 Aspect가 적용된 메서드가 수행된다. (호출하지 않으면 수행되지 않음)
        joinPoint.proceed();

        // Aspect가 적용된 메서드가 리턴된 이후에 할 작업은 proceed() 호출 이후에 한다.
        System.out.println("Aspect가 적용된 메서드가 리턴됐습니다.");
    }

    /*
     *  return type은 String이고
     *  get으로 시작되는 메서드이고
     *  메서드에 전달되는 인자는 없다.
     *  java.lang 패키지에 있는 type은 패키지명 생략 가능
     *  com.example.boot08.util 패키지에 존재하는 모든 클래스의 메서드 중 get으로 시작하는 메서드
     */
    @Around("execution(String com.example.boot08.util.*.get*())")
    public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        // Aspect가 적용된 메서드를 수행하고 리턴되는 데이터 받아오기
        Object obj = joinPoint.proceed();

        // 원래 type으로 casting해서 조사해볼 수 있다.
        String a = (String) obj;

        // return obj;
        // 조사후 아예 다른 값을 리턴해 줄 수도 있다.
        return "놀자놀자";
    }
}
