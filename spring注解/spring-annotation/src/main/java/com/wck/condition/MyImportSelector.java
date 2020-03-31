package com.wck.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 御香烤翅
 * @create 2020-03-10 10:55
 */
//自定义逻辑，返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 标注的@Import注解类上的所有注解的信息
     * @return 返回值，就是导入到容器中组件的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.wck.bean.Blue","com.wck.bean.Yellow"};
    }
}
