package cn.zjcw.wlt.starter.encrypt.mysql;

/**
 *
 * 加密算法
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
public enum CryptoType {

    AES("AES", "AES");


    private String code;
    private String message;

    CryptoType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CryptoType getByCode(final String code) {

        final CryptoType[] values = CryptoType.values();

        for (final CryptoType value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
