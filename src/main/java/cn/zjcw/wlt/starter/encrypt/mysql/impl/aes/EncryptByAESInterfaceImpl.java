package cn.zjcw.wlt.starter.encrypt.mysql.impl.aes;

import cn.hutool.core.util.StrUtil;
import cn.zjcw.wlt.starter.encrypt.mysql.EncryptInterface;
import mybatis.mate.encrypt.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * AES 实现加密算法
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-117
 *
 */
public class EncryptByAESInterfaceImpl implements EncryptInterface {

    private static final Logger logger = LoggerFactory.getLogger(EncryptByAESInterfaceImpl.class);

    @Value("${mybatis-mate.encryptor.password:}")
    private String aesKey;

    @Override
    public String encrypt(String content) {
        if(StrUtil.isEmpty(aesKey)){
            return null;
        }
        try {
            logger.info("AES-数据加密 content {}", content);
            return AES.encrypt(aesKey, content);
        } catch (Exception e) {
            logger.error("AES-数据加密异常 {}", e);
            return null;
        }
    }
}
