package cn.zjcw.wlt.starter.encrypt.mysql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 自动加密拦截器声明（方法）
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutoEncryptMethod {

}
