package com.changlu.common.easycaptcha.service;

import com.changlu.common.easycaptcha.enums.CodeTypeEnum;
import com.changlu.common.easycaptcha.producer.EasyCaptchaProducer;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EasyCaptchaService
 * @Author ChangLu
 * @Date 4/12/2022 6:20 PM
 * @Description easy-captcha业务工具类
 */
@Service
public class EasyCaptchaService {

    @Autowired
    private EasyCaptchaProducer easyCaptchaProducer;

    /**
     * 获取指定类型的验证码结果以及Base64编码值
     * @param codeType 验证码类型
     * @return
     */
    public Map<String,String> getCaptchaValueAndBase64(CodeTypeEnum codeType){
        Captcha captcha = null;
        if (codeType == null){
            captcha = easyCaptchaProducer.getCaptcha();
        }else {
            captcha = easyCaptchaProducer.getCaptcha(codeType);
        }
        //1、获取到结果值
        String captchaValue = captcha.text();
        //对于数学类型的需要进行处理
        if (codeType == null || codeType == CodeTypeEnum.ARITHMETIC) {
            if (captcha.getCharType() - 1 == CodeTypeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
        }
        //2、获取到Base64编码
        String captchaBase64 = captcha.toBase64();
        Map<String,String> result = new HashMap<>(2);
        result.put("code", captchaValue);
        result.put("base64", captchaBase64);
        return result;
    }

}
