package com.changlu.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //令牌自定义标识
    public static String header = "Authorization";

    //有效期为30分钟
    public static final int JWT_EXPIRETIME = 30;

    //1秒钟的1000毫秒
    public static final long MILLIS_SECOND = 1000;

    //1分钟的毫秒数
    public static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    //设置秘钥明文
    public static final String JWT_KEY = "changlu";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        //若是带有超时时间的生成有时间对其进行签发时间配置
        if (ttlMillis != null){
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            if(ttlMillis==null){
                ttlMillis = (long) JwtUtil.JWT_EXPIRETIME;
            }
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            return Jwts.builder()
                    .setId(uuid)              //唯一的ID
                    .setSubject(subject)   // 主题  可以是JSON数据
                    .setIssuer("changlu")     // 签发者
                    .setIssuedAt(now)      // 签发时间
                    .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                    .setExpiration(expDate);  //设置过期时间
        }
        //若是没有设置指定时间，就直接生成一个不带有过期时间的token
        return Jwts.builder()
                .setId(uuid) //设置唯一标识
                .setIssuer("changlu")
                .setSubject(subject) //设置主体内容
                .signWith(signatureAlgorithm, secretKey);//进行算法签名
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey，用于对指定key进行加密作为我们的秘钥
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                //解析的过程中就包含了对token的校验
                .parseClaimsJws(jwt)
                .getBody();
    }

    //测试
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        final String token = createJWT("changlu");
        System.out.println(token);
        //校验token
        final Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyNTA2ODZmMzBlNTY0ODdhOGNlYTU1YWQxYzIzYWE0MiIsImlzcyI6ImNoYW5nbHUiLCJzdWIiOiJjaGFuZ2x1In0.RE3dkDuGg-bTPI5ipwi6JFrhoPVN94ar_Pe0vsPAchg");
        //claims包含五个键值，getSubject就是获取key为sub，也就是我们在createJWT传入的内容
        System.out.println("claims：" + claims);//结果：claims：{jti=f397df28207848c19b5d807b4a26ada3, sub=changlu, iss=sg, iat=1648178781, exp=1648182381}
        System.out.println("subject：" + claims.getSubject());// subject：changlu
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYWM2ZDVhZi1mNjVlLTQ0MDAtYjcxMi0zYWEwOGIyOTIwYjQiLCJzdWIiOiJzZyIsImlzcyI6InNnIiwiaWF0IjoxNjM4MTA2NzEyLCJleHAiOjE2MzgxMTAzMTJ9.JVsSbkP94wuczb4QryQbAke3ysBDIL5ou8fWsbt_ebg";
//        Claims claims = parseJWT(token);
//        System.out.println(claims);
    }


    public static void testToken() throws Exception {
        System.out.println(parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwYTRhMTdjNjVmZmI0MjliYjZjODJlZDMyNjM4ODI4NSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY0ODE5MzIwMSwiZXhwIjoxNjQ4MTk2ODAxfQ.M5xKYLYNOu-bnU7FUGYnx5hsBc-wSAtNOnkSxYhCT1I").getSubject());
    }

}
