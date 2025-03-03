package com.example.computer_management.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ComputerAspect {

    @Before(value = "executeController()")
    public void beforeExecuteController(){
        System.out.println("Inside beforeController");
    }


   @Pointcut(value = "within(com.example.computer_management.controller.*)")
    public void executeController(){

   }

}
