package cn.zjcw.wlt.starter.encrypt.mysql.factory;

import cn.zjcw.wlt.starter.encrypt.mysql.AutoEncrypt;
import cn.zjcw.wlt.starter.encrypt.mysql.AutoEncryptAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 自动加密拦截器声明
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
@Configuration
@ConditionalOnMissingBean(annotation = {AutoEncrypt.class})
public class AspectFactory {
    @Bean
    static AutoEncryptAspect autoEncryptAspect() {
        return new AutoEncryptAspect();
    }
}
