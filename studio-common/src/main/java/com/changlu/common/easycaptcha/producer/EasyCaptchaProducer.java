package com.changlu.common.easycaptcha.producer;

import com.changlu.common.easycaptcha.config.EasyCaptchaConfig;
import com.changlu.common.easycaptcha.enums.CodeTypeEnum;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName EasyCaptchaProducer
 * @Author ChangLu
 * @Date 4/12/2022 5:35 PM
 * @Description 验证码生成器
 */
@Component
public class EasyCaptchaProducer {

    @Autowired
    private EasyCaptchaConfig easyCaptchaConfig;

    /**
     * 自定义选择类型
     */
    public Captcha getCaptcha(CodeTypeEnum codeType){
        easyCaptchaConfig.setCodeType(codeType);
        return switchCaptcha(easyCaptchaConfig);
    }

    /**
     * 获取默认配置captcha
     */
    public Captcha getCaptcha(){
        return switchCaptcha(easyCaptchaConfig);
    }


    private Captcha switchCaptcha(EasyCaptchaConfig config) {
        Captcha captcha;
        switch (config.getCodeType()) {
            case ARITHMETIC:
                // 算术类型 https://gitee.com/whvse/EasyCaptcha
                captcha = new FixedArithmeticCaptcha(config.getWidth(), config.getHeight());
                //固定设置为两位，图片为算数运算表达式
                captcha.setLen(2);
                break;
            case CHINESE:
                captcha = new ChineseCaptcha(config.getWidth(), config.getHeight());
                captcha.setLen(config.getLength());
                break;
            case CHINESE_GIF:
                captcha = new ChineseGifCaptcha(config.getWidth(), config.getHeight());
                captcha.setLen(config.getLength());
                break;
            case GIF:
                captcha = new GifCaptcha(config.getWidth(), config.getHeight());//最后一位是位数
                captcha.setLen(config.getLength());
                break;
            case SPEC:
                captcha = new SpecCaptcha(config.getWidth(), config.getHeight());
                captcha.setLen(config.getLength());
                break;
            default:
                throw new RuntimeException("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
        }
        return captcha;
    }

    static class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width, int height) {
            super(width, height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }

}
