package com.changlu.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName RsaUtil
 * @Author ChangLu
 * @Date 4/12/2022 4:00 PM
 * @Description 非对称加解密工具类
 */
public class RsaUtil {

    private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoUa35BaU80JrXMZIOfPxKBIZqMzBe7dg7TFCp8Bn3epPMTxCeJszXwB74wc67JYED03KuMaK/KJPAql/uF487zKS/Hv3DltbVauViQSt2ti85Rf7Gj5nJbKwjQRV+2TfiRhbYVzkKP9LruWne5cKhUaHcw9GlxtrwBATO3RhtSiaXHYFErNWNVhqkWRp9I/ugJNRT6mkz2GUwY/GWRZzaFoaNVlQOYKzoq8xu1buupiB4vg5A4uTyPfCjQtsrMa4jULbQ9mqVbCmja40AX21jqN256BoXwvPSNWkRKvULs0ANQjK8ICySuE4WIo30dy7px2Thyef887KlO9cBA7wWwIDAQAB";

    private static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChRrfkFpTzQmtcxkg58/EoEhmozMF7t2DtMUKnwGfd6k8xPEJ4mzNfAHvjBzrslgQPTcq4xor8ok8CqX+4XjzvMpL8e/cOW1tVq5WJBK3a2LzlF/saPmclsrCNBFX7ZN+JGFthXOQo/0uu5ad7lwqFRodzD0aXG2vAEBM7dGG1KJpcdgUSs1Y1WGqRZGn0j+6Ak1FPqaTPYZTBj8ZZFnNoWho1WVA5grOirzG7Vu66mIHi+DkDi5PI98KNC2ysxriNQttD2apVsKaNrjQBfbWOo3bnoGhfC89I1aREq9QuzQA1CMrwgLJK4ThYijfR3LunHZOHJ5/zzsqU71wEDvBbAgMBAAECggEAYuf73uX6czVIopz3rpLsUqbHFb586mKMDusm9boJ+Q65gnhyS4Ti/GBPmevVN0LJOpl6udcJZeYkjn1k1qz0sb1SZ4We/i8I1IFmM6Gq/YvuTJFoba/XkkcD/3epQ4OZk3pckFlnk7z9rUUqcaEv0oxuhSHZKNKoQiGZqdy2D2LcIra5iSsx4o8q+OkTG0D2fM8Xy0Ov4RZMKVusmncBt4NCbb/zweKg5XokOHUlV0ieE8Kqo26WzUtNwbaSsVGFDurq1LH8QnnW4e8JRIG/RQfImXNvlwkgRRAHVf0nlamSS8kzX0bu9NMibAz5aJ7xFjjVyN3Ib4+IjxZsPhWgAQKBgQDmA2ao2NLgYoRE5ibFBZ1mbaxNGnYu5PyxGl4PEisbLzO5joUEOqY1ufo3EmAHBZ7hGNwQ6pjPcoyF2BAMz3Nm7pFbTgWCtpbJJ4NU2WD5pOYsZk2tQ9zVsRTXbr6aIsU/M7PteZajUy84x0CAOGe3P6IeF6XC63tcrogrXEaXUQKBgQCzf0Iiv8HrIyCyqXuHjjVFpGVBL7oDio7+96dijM6hTYAZAH7iz9b4jtprqZ5Bt4kvsQkB8adjwJi4jDClUUXtimnLYHKeuhGM6p0k8Wr6PULpRj150QnvT7NE2SjCFHtFJkB9ALvndANg/91z/zZ76ak9/DkxVGcL3t5AbEE56wKBgHROlfOdKAykZEO+qS4JJ80qeezHC6msyoX3UkaGpltpo9sZUPORgIg8UWTVms8SLYOfIXBAYCLNxnbLiVog8DbFPaUQr8Kn91kh7MvT27CAjI847+BEJ3+6ulIxQpoIKPtLdpkHWX+eyYsJXfk4DGHiAGT9CimlD00PR/W9BEjxAoGBAJ/SMp/gH5ju6SFLWMsk6vf8XY/CIB0hFRuehPJxr1XePK2GTHRFXQJpp79vApM/CS5msj+B3GosRY8ke/fmup27vWExmj0MLa5MA+mt73OqclZkjT5Wy0LCszsdJHZSaEP0wDzNF4IJQSzwBU1QM0H3lhKz7nSm0y21niy8dwNLAoGARCxu8ataWhZPU5oFECqio4Lz5s13cl7rFPj44RHziYNXMOGdsNAYs4T2qJfIikJUIXu65N+1aJbCiFidxjS28dbXVbXJGblfq9p3fbihPTlp5j2j7QPWGXpNYg+jG4qarkO/1WgRBBYurnfBANlhdQ/0+GXRGK92rm/b3yCai+A=";

    /**
     * RSA密钥对对象
     */
    public static class RSAKeyPair {

        private String publicKey;
        private String privateKey;

        public RSAKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

    }

    /**
     * 私钥解密
     *
     * @param privateKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText
     * @param text
     * @return
     */
    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    public static void main(String[] args) throws Exception{
        String password = "changlu";
//        String encryptPassword = encryptByPublicKey(RsaUtil.publicKey, password);
        String encryptPassword = "R1AcGyb6KT6b5HSTDLFvAPJhzutI1v6OY8OLsIKOpnrRufJAM29m9p9KcxfxFiw2TYgHmjl3FPYqjcFYaMfSGZQPmV9pv5Ys96fXawpJyyjrVwQhz2mUGeZG1TW8/p95RdXZNtAr+vShVZSvSdh0Tga050XKnSzLo6prN0+U/9X+tl3PfvFjJ9Jc4rWgjCjgOcMI6E4qb9LzSEO/szi8y2mgEuWIiDgGWe2ZgmfIS6/97M6m1R1kJH2+rIfDsMHoIx6CBbloW+RoyCGuhG5P25E+K1Eg/SnagKO3/72uraJwnfG0g2LdJNyeI0KcMmHNiNP8kIRnloqeHk0bCSNsjg==";
        String decodePassword = decryptByPrivateKey(encryptPassword);
        System.out.println(decodePassword);
    }


}
