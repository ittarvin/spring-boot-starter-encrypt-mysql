package cn.zjcw.wlt.starter.encrypt.mysql;

import cn.hutool.core.collection.CollectionUtil;
import cn.zjcw.wlt.starter.encrypt.mysql.factory.CryptoFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * 实现加密算法拦截器
 *
 * @author wangpengfei(鲁米)
 * @date 2023-03-17
 *
 */
@Aspect
@Slf4j
public class AutoEncryptAspect {

    @Pointcut("@annotation(cn.zjcw.wlt.starter.encrypt.mysql.AutoEncryptMethod)")
    public void autoEncryptCut() {
    }

    @Around("autoEncryptCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Object[] args = pjp.getArgs();
        //加密工程无处理器
        if(CollectionUtil.isEmpty(CryptoFactory.encryptInterfaceMap.keySet())){
            try {
                return pjp.proceed(args);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }finally {

            }
        }
        //处理方法参数
        for (Object _obj : args) {
            Class<?> resultClz = _obj.getClass();
            Field[] fieldInfo = resultClz.getDeclaredFields();
            handleObj(_obj,fieldInfo,resultClz);
        }
        try {
            return pjp.proceed(args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {

        }
    }

    /**
     * 处理单个对象-对象类型需要继续遍历
     * @param _obj
     * @param fieldInfo
     * @param resultClz
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void handleObj(Object _obj,Field[] fieldInfo,Class<?> resultClz) throws IllegalAccessException, NoSuchFieldException {
        for (Field field : fieldInfo) {
            Annotation[] annotations = field.getAnnotations();
            if(annotations == null || annotations.length == 0){
                continue;
            }
            for (int i = 0; i < annotations.length; i++) {
                field.setAccessible(true);
                Object ret = field.get(_obj);
                Class<?> resultClzObj = ret.getClass();
                Field[] fieldInfoObj = resultClzObj.getDeclaredFields();
                if(annotations[i].annotationType().equals(Encrypt.class)){
                    //对象类型
                    handleObj(ret,fieldInfoObj,resultClzObj);
                } else if(annotations[i].annotationType().equals(AutoEncrypt.class)){
                    //值类型
                    AutoEncrypt encrypts = field.getAnnotation(AutoEncrypt.class);
                    String fieldName = field.getName()+encrypts.value().getCode();
                    Object fieldValue = field.get(_obj);
                    String encrypt = CryptoFactory.encrypt(fieldValue.toString(),encrypts.value());
                    Field aesField = resultClz.getDeclaredField(fieldName);
                    aesField.setAccessible(true);
                    aesField.set(_obj,encrypt);
                }
            }
        }
    }

}
