package com.wck.starter;

/**
 * @author 御香烤翅
 * @create 2020-03-17 23:14
 */
public class HelloService {

    HelloProperties helloProperties;

    public String sayHello(String name){
        //返回一个加了前缀和后缀的名称
        return helloProperties.getPrefix()+"-"+name+"-"+helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
