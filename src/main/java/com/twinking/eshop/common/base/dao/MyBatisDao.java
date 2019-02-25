package com.twinking.eshop.common.base.dao;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Description:  Dao标识注解
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/9/10 13 28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisDao {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";

}