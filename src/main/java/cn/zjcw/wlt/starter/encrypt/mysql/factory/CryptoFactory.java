package cn.zjcw.wlt.starter.encrypt.mysql.factory;

import cn.zjcw.wlt.starter.encrypt.mysql.*;
import cn.zjcw.wlt.starter.encrypt.mysql.impl.aes.EncryptByAESInterfaceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * 自动加密工程类
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
@Configuration
@ConditionalOnMissingBean(annotation = {AutoEncrypt.class})
public class CryptoFactory implements InitializingBean {

    /**
     * 加密秘实现方法结合 key 为 CryptoType
     */
    public static Map<CryptoType, EncryptInterface> encryptInterfaceMap
            = new ConcurrentHashMap<>(1);

    /**
     * 加密实现 AES 方式
     *
     * @return
     */
    @Bean(name = "encryptByAES")
    static EncryptInterface encryptByAES() {
        return new EncryptByAESInterfaceImpl();
    }

    @Autowired
    @Qualifier("encryptByAES")
    EncryptInterface encryptByAES;


    /**
     * 数据加密
     *
     * @param content
     * @param cryptoType
     * @return
     */
    public static String encrypt(String content,
                                 CryptoType cryptoType) {
        EncryptInterface encrypt = encryptInterfaceMap.get(cryptoType);
        return encrypt.encrypt(content);
    }


    /**
     * 初始化工程类成员
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        encryptInterfaceMap.putIfAbsent(CryptoType.AES, encryptByAES);
    }


}
