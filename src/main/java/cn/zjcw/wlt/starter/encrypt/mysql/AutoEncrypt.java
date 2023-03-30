package cn.zjcw.wlt.starter.encrypt.mysql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 自动加密拦截器声明（字段）
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoEncrypt {

    /**
     * 加密类型
     *
     * @autor 王鹏飞
     */
    CryptoType value() default CryptoType.AES;
}
