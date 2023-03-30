package cn.zjcw.wlt.starter.encrypt.mysql;

/**
 *
 * 自动加密接口声明
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
public interface EncryptInterface {

    /**
     * 数据加密
     *
     * @param content
     * @return
     */
    String encrypt(String content);

}
