package com.wck.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author 御香烤翅
 * @create 2020-03-10 22:48
 */

/**
 * @Aspect：告诉spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用 使用全类名的方式  如：logEnd这个方法的使用

    //抽取公共的切入点表达式

    @Pointcut("execution(public int com.wck.aop.MathCalculator.*(..))")
    public void pointCut(){};

    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("@Before "+joinPoint.getSignature().getName()+"运行前...参数列表是{"+Arrays.asList(args) +"}");
    }

    //目标方法运行之后调用这个方法
    @After("com.wck.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){//JoinPoint 一定要放在参数表的第一位
        System.out.println("@After "+joinPoint.getSignature().getName()+"运行结束");
    }

    //目标方法运行正常调用这个方法
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println("@AfterReturning "+joinPoint.getSignature().getName()+"正常返回...运行结果：{"+result+"}");
    }

    //目标方法运行异常调用这个方法
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("@AfterThrowing "+joinPoint.getSignature().getName()+"异常...异常信息：{"+exception.getMessage()+"}");
    }

}
