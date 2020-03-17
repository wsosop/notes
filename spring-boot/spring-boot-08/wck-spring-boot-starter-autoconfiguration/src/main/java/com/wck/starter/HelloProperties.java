package com.wck.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 御香烤翅
 * @create 2020-03-17 23:15
 */

//所有以wck.hello开头的配置文件
@ConfigurationProperties(prefix = "wck.hello")
public class HelloProperties {

    private String prefix;//前缀
    private String suffix;//后缀

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
