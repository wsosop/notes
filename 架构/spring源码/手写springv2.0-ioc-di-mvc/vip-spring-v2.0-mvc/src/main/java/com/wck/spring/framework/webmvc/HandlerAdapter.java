package com.wck.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-02-19 14:33
 * 为甚么要把 handler传进来？
 * 因为handler 中包含了 controller，method,和url 信息
 * 为了解耦，专人干专事
 */
public class HandlerAdapter {


    private Map<String,Integer> paramMapping;

    public HandlerAdapter(Map<String,Integer> paramMapping){
        //System.out.println("paramMapping:"+paramMapping);
        this.paramMapping=paramMapping;
    }



    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws Exception{
        //根据用户的请求request信息和method中的参数信息进行动态匹配
        //resp传进来的目的只有一个，只是为了将其赋值给方法参数，仅此而已

        //1.要准备好这个方法的形参列表
        //方法的重载，形参的决定因素：
        //参数的个数，参数的类型，参数的顺序，方法的名字
        Class<?>[] paramTypes=handler.getMethod().getParameterTypes();
        //2.拿到自定义命名参数所在位置
        Map<String,String[]> reqParamValues=req.getParameterMap();

        //3.构造实参列表
        Object[] paramValues=new Object[paramTypes.length];

        for(Map.Entry<String,String[]> param:reqParamValues.entrySet()){

            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","").replaceAll("\\s","");

            //如果方法中定义的RequestParam 中有和请求中的参数key 相同的 则取出这个值
            if(paramMapping.containsKey(param.getKey())){

                //因为页面上传过来的值都是String类型的，而在方法中定义的类型是千变万化的
                //要对我们传过来的类型进行类型转换
                int index=this.paramMapping.get(param.getKey());
                paramValues[index]=caseStringValue(value,paramTypes[index]);
            }
        }

        //处理HttpServletRequest
        if(this.paramMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex=this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex]=req;

        }

        //处理HttpServletResponse
        if(this.paramMapping.containsKey(HttpServletResponse.class.getName())){
            int resqIndex=this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[resqIndex]=resp;
        }
        //4.从handler中取出 controller、method和pattern,然后利用反射机制进行调用

        Object result=handler.getMethod().invoke(handler.getController(),paramValues);

        if(result == null){
            return null;
        }

        //只有当用户传递进来的modelAndView为空的时候，才会new一个默认的
        boolean isModelAndView=ModelAndView.class==handler.getMethod().getReturnType();
        if(isModelAndView){
            return (ModelAndView) result;
        }else{
            return  null;
        }
    }

    private Object caseStringValue(String value,Class<?> clazz){

        if(clazz == String.class){
            return value;
        }else if(Integer.class == clazz){
            return Integer.valueOf(value);
        }else if(clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }

    }



}
