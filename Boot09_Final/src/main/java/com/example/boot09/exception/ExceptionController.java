package com.example.boot09.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Spring MVC가 동작 중 특정 type의 예외가 발생하면 해당 예외를 여기서 처리할 수 있다.
@ControllerAdvice
public class ExceptionController {
    
    // Spring Framework가 동작하는 중에 NotOwnerException type의 예외가 발생하면 호출되는 메서드 
    @ExceptionHandler(NotOwnerException.class)
    public String notOwner(NotOwnerException noe, Model model) { // 메서드의 매개 변수에 예외 객체의 참조값이 전달된다.
        // "exception"이라는 키값으로 예외 객체를 담는다. 
        model.addAttribute("exception", noe);
        
        // view 페이지에서 에러 정보를 응답한다.
        return "error/info";
    }
}
