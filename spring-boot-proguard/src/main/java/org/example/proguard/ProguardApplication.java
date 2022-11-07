package org.example.proguard;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@SpringBootApplication
public class ProguardApplication {

    public static void main(String[] args) {
        // SpringApplication.run(ProguardApplication.class, args);
        new SpringApplicationBuilder(ProguardApplication.class)
                .beanNameGenerator(new UniqueNameGenerator())
                .run(args);
    }

    /**
     * 由于需要混淆代码,混淆后类都是A B C,spring 默认是把A B C当成BeanName,BeanName又不能重复导致报错
     * 所以需要重新定义BeanName生成策略
     * 不能重写generateBeanName方法,因为有些Bean会自定义BeanName,所以这些情况还需要走原来的逻辑
     */
    @Component("UniqueNameGenerator")
    public static class UniqueNameGenerator extends AnnotationBeanNameGenerator
    {
        /**
         * 重写buildDefaultBeanName
         * 其他情况(如自定义BeanName)还是按原来的生成策略,只修改默认(非其他情况)生成的BeanName带上包名
         */
        @Override
        public @Nullable String buildDefaultBeanName(BeanDefinition definition)
        {
            //全限定类名
            return Objects.requireNonNull(definition.getBeanClassName());
        }
    }

}
