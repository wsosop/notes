package com.wck.spring.framework.webmvc.servlet;

import com.wck.spring.framework.annotation.Controller;
import com.wck.spring.framework.annotation.RequestMapping;
import com.wck.spring.framework.annotation.RequestParam;
import com.wck.spring.framework.context.ApplicationContext;
import com.wck.spring.framework.webmvc.HandlerAdapter;
import com.wck.spring.framework.webmvc.HandlerMapping;
import com.wck.spring.framework.webmvc.ModelAndView;
import com.wck.spring.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 御香烤翅
 * @create 2020-02-16 19:21
 */
public class DispatchServlet extends HttpServlet {


    //application.properties 配置文件的初始化 init-param 名称：contextConfigLocation
    private final String LOCATION="contextConfigLocation";

    //用于存放处理 匹配的url 和对应的方法和controller
    private List<HandlerMapping> handlerMappings=new ArrayList<HandlerMapping>();

    //用于存放方法的RequestParam参数
    private Map<HandlerMapping,HandlerAdapter> handlerAdapters=new HashMap<>();

    //用于存放视图列表
    private List<ViewResolver> viewResolvers=new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");

        try {
            doDispatch(req,resp);

        }catch (Exception e){
            resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s","\r\n") +  "<font color='green'><i>Copyright@WCK</i></font>");
            e.printStackTrace();
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init...");
        ApplicationContext applicationContext=new ApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(applicationContext);
    }

    /**
     * springMVC的核心启动，也即springMVC的九大组件
     * @param context
     */
    protected void initStrategies(ApplicationContext context) {
        //有九种策略
        //针对于每个用户请求,都会经过一些处理的策略之后,最终才能有结果输出
        // 种策略可以自定义,但是最终的结果都是一致
        // ModeLAndview
        //======这里说的就是传说中的九大组件======

        //文件上传解析，如果请求类型是multipar，将通过initMultipartResolver进行文件上传解析
        initMultipartResolver(context);
        //本地化解析
        initLocaleResolver(context);
        //主题解析
        initThemeResolver(context);

        /*我们自己会实现*/
        //通过HandlerMapping，将请求映射到处理器，主要是 requestMapping和方法进行一一对应关系
        initHandlerMappings(context);
        /*我们自己会实现*/

        //通过HandlerAdapter进行多类型的参数动态匹配
        initHandlerAdapters(context);

        //如果执行过程中出现异常，将交给HandlerExceptionResolver来进行解析
        initHandlerExceptionResolvers(context);

        //直接解析请求到视图名
        initRequestToViewNameTranslator(context);

        /*我们自己会实现*/
        //通过ViewResolver解析逻辑视图到具体视图实现
        initViewResolvers(context);
        /*我们自己会实现*/

        //flash映射管理器
        initFlashMapManager(context);

    }



    private void initMultipartResolver(ApplicationContext context) { }
    private void initLocaleResolver(ApplicationContext context) {}
    private void initThemeResolver(ApplicationContext context) {}


    //匹配url和方法之间的初始化处理
    private void initHandlerMappings(ApplicationContext context) {

        //获取所有的BeanDefinitionNames
        //首先从容器中取到所有的实例
        String[] beanNames=context.getBeanDefinitionNames();
        for (String beanName:beanNames) {
            //获取相对应的beanName的实例
            Object controller=context.getBean(beanName);
            Class<?> clazz=controller.getClass();
            //不是Controller 注解
            if(!clazz.isAnnotationPresent(Controller.class)){
                continue;
            }

            String baseUrl="";

            //是RequestMapping注解
            if(clazz.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
                baseUrl=requestMapping.value();//获取controller中的RequestMapping注解的值
            }

            //扫描所有的public 方法
            Method[] methods=clazz.getMethods();
            for(Method method:methods){
                //如果方法中有包含RequestMapping 注解，则把值取出来拼接在 controller的RequestMapping后面
                if(!method.isAnnotationPresent(RequestMapping.class)){
                    continue;
                }

                if(method.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                    String regex=("/"+baseUrl+requestMapping.value()).replaceAll("/+","/");

                    Pattern pattern=Pattern.compile(regex);

                    this.handlerMappings.add(new HandlerMapping(pattern,controller,method));
                    System.out.println("initHandlerMappings->mapping:"+regex+" , "+method);
                }
            }
        }
        System.out.println("至此initHandlerMappings已经初始完毕："+this.handlerMappings);
    }

    //方法中的请求参数的初始化的处理包括标签和无标签
    private void initHandlerAdapters(ApplicationContext context) {
        //在初始化阶段，我们能做的就是，将这些参数的名字或者类型按照一定的顺序保存下来
        //因为后面用反射调用的时候，传的形参是一个数组
        //可以通过记录这些参数的位置index,挨个的从数组中进行填值，这样的话，就和参数的顺序无关了

        for(HandlerMapping handlerMapping:this.handlerMappings){
            //每一个方法有一个参数列表，那么这里保存的是形参列表
            Map<String,Integer> paramMapping  =new HashMap<>();

            //这里只是处理了命名的参数
            Annotation[] [] pa=handlerMapping.getMethod().getParameterAnnotations();
            //打印二位数组，Arrays.deepToString
            //System.out.println("annotations:"+handlerMapping.getController().getClass().getName()+"--->"+Arrays.deepToString(annotations));

            for(int i=0;i<pa.length;i++){
                for(Annotation a:pa[i]){
                    if(a instanceof RequestParam){
                        String paramName=((RequestParam) a).value();
                        if(!"".equals(paramName)){
                            paramMapping.put(paramName,i);
                        }
                    }
                }
            }

            //接下来，我们处理非命名参数，这里我们只认为 处理 HttpServletRequest 和 HttpServletResponse

            Class<?>[] paramTypes= handlerMapping.getMethod().getParameterTypes();
            //System.out.println("Arrays.toString(paramTypes):"+Arrays.toString(paramTypes));
            for(int i=0;i<paramTypes.length;i++){
                Class<?> type=paramTypes[i];
                if(type == HttpServletRequest.class ||
                        type == HttpServletResponse.class){
                    paramMapping.put(type.getName(),i);

                }
            }

            /**
             * System.out.println("paramMapping:"+paramMapping);打印结果如下：
             * paramMapping:{javax.servlet.http.HttpServletRequest=0, name=2, javax.servlet.http.HttpServletResponse=1, addr=3}
             * paramMapping:{javax.servlet.http.HttpServletRequest=0, javax.servlet.http.HttpServletResponse=1, id=2}
             * paramMapping:{javax.servlet.http.HttpServletRequest=0, name=2, javax.servlet.http.HttpServletResponse=1}
             * paramMapping:{javax.servlet.http.HttpServletRequest=0, name=3, javax.servlet.http.HttpServletResponse=1, id=2}
             * paramMapping:{teacher=0}
             */
            this.handlerAdapters.put(handlerMapping,new HandlerAdapter(paramMapping));
        }

    }


    //逻辑视图到具体的视图初始化实现
    private void initViewResolvers(ApplicationContext context) {
        //在页面敲一个 http://127.0.0.1:8080/first.html
        //解决页面名字和模板文件关联的问题
        Properties properties=context.getConfig();
        String templateRoot=properties.getProperty("templateRoot");

        String templateRootFile =this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir=new File(templateRootFile);

        for(File template:templateRootDir.listFiles()){
            this.viewResolvers.add(new ViewResolver(template.getName(),template));
        }

    }

    private void initHandlerExceptionResolvers(ApplicationContext context) {}
    private void initRequestToViewNameTranslator(ApplicationContext context) {}
    private void initFlashMapManager(ApplicationContext context) {}


    //处理请求
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        //1.根据用户请求的url来获得 Handler
        HandlerMapping handler=getHandler(req);
        System.out.println("============handler:"+handler);
        if(handler == null){
            resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/><font color='green'><i>Copyright@WCK</i></font>");
            return;
        }

        //获取当前handler的HandlerAdapter
        HandlerAdapter ha = getHandlerAdapter(handler);

        //这一步只是调用方法，得到返回值,具体的就是利用反射调用了方法，
        // 然后拿到浏览器中用户输入的参数，当实际的参数，传递给方法的形参
        ModelAndView mv=ha.handle(req,resp,handler);

        //这一步才是真正的输出
        //此方法是用于使用mv之中model的数据把，相对应的数据替换输出到模板（html）文件中
        //最后完成输出
        processDispatchResult(resp,mv);

        //最终访问：http://127.0.0.1:8080/first.html?student=wck
        //可以看到效果



    }


    /**
     * 根据用户的请求，来获得一个Handler
     * @param req
     * @return
     */
    public HandlerMapping getHandler(HttpServletRequest req){

        if(this.handlerMappings.isEmpty()){
            return null;
        }

        String url=req.getRequestURI();
        System.out.println("请求的URI:"+url);
        String contextPath=req.getContextPath();
        System.out.println("请求的contextPath:"+url);

        url=url.replace("contextPath","").replaceAll("/+","/");
        System.out.println("最终替换好的url:"+url);
        for(HandlerMapping handlerMapping:this.handlerMappings){

            Matcher matcher=handlerMapping.getPattern().matcher(url);
            if(!matcher.matches()){
                continue;
            }

            return  handlerMapping;
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){
            return null;
        }

        return this.handlerAdapters.get(handler);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws Exception{

        //这里会调用 ViewResolver 的resolverView方法
        //如果传递的不是ModelAndView，直接返回
        if(mv == null){
            return;
        }

        //如果视图配置的路径下，没有视图的模板文件（也就是html）,直接返回
        if(this.viewResolvers.isEmpty()){
            return;
        }

        //当前的modelAndView和相对应的模板（html）进行匹配
        for(ViewResolver viewResolver:this.viewResolvers){

            if(!mv.getViewName().equals(viewResolver.getViewName())){
                continue;
            }
            //直接输出
            String out=viewResolver.viewResolver(mv);
            if(out != null){
                resp.getWriter().write(out);
            }
        }



    }

}

