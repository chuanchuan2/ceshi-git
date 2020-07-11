package com.cloud.consumer.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import static org.apache.catalina.manager.Constants.CHARSET;

public class RSAUtil {

    //加密算法RSA
    public static final String KEY_ALGORITHM = "RSA";

    //签名算法
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    //获取公钥的key
    private static final String PUBLIC_KEY = "RSAPublicKey";

    //获取私钥的key
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /* 如何将文件形式的公钥私钥转成字符串形式的公钥私钥
     其实转为字符串形式，我们主要是要从公钥私钥对象中，得到两个参数，模量modulus和指数系数exponent，
     这两参数能重新构成公钥私钥对象，从而进行加解密*/
    public static KeyPair generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象   RSA为自己指定的数据签名算法名称*/
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        final int KEY_SIZE = 1024;//定义密钥的大小
        kpg.initialize(KEY_SIZE, sr);
        /** 生成密匙对 */
        KeyPair keyPair = kpg.generateKeyPair();
        System.out.println("=================="+keyPair);

        /* *//** 1.得到公钥 *//*  可以写入文件中例子
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        *//** 2.得到私钥 *//*
        Key privateKey = keyPair.getPrivate();
        *//** 用对象流将生成的密钥写入文件 *//*
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("publickey.keystore"));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("privatekey.keystore"));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        *//** 清空缓存，关闭文件输出流 *//*
        oos1.close();
        oos2.close();*/
        return keyPair;
    }

    /**
     * 生成公钥
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        //h获取秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
        //这里可以两种方式获取 RSAPublicKeySpec 第一种这里不用
        //RSAPublicKeySpec keySpec = (RSAPublicKeySpec)keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
        //第二种这里通过构造的方式创建
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        /** 生成公钥 */
        return (RSAPublicKey) keyFactory.generatePublic(rsaPublicKeySpec);

    }

    /**
     * 生成私钥
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] publicExponent) throws Exception {
        //h获取秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
        //第二种这里通过构造的方式创建
        RSAPrivateKeySpec rsaPublicKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        /** 生成私钥 */
        return (RSAPrivateKey) keyFactory.generatePublic(rsaPublicKeySpec);
    }


    //

    /**
     * 加密密码
     * publicKey 加密的密钥
     * data 待加密的明文数据
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
            /*此类为加密和解密提供密码功能。它构成了 Java Cryptographic Extension (JCE) 框架的核心。
            为创建 Cipher 对象，应用程序调用 Cipher 的 getInstance 方法并将所请求转换 的名称传递给它。
            还可以指定提供者的名称（可选）*/
            Cipher cipher = Cipher.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //获取加密块的大小
            /*如：加密前数据为128byte，而key_size=1024
            加密快大小为127，byte加密数据为128个byte；因此共有两个加密块，第一个为127byte，第二个为1byte*/
            int blockSize = cipher.getBlockSize();//分组加密中，每一组都有固定的长度,获取加密块的大小
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leaveSize = data.length % blockSize;//取余判断下面根据判断计算加密块的数量
            //计算得来加密块数量
            int blockSizeCount = leaveSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blockSizeCount];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                i++;
                /*这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作
                除了把byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，
                可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。*/
        }
        return raw;
    }

    /**
     * 解密
     * @param privateKey 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] raw) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int blockSize = cipher.getBlockSize();
        ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
        int j = 0;
        while (raw.length - j * blockSize >= 128) {
            try {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            } catch (Exception e) {
                e.printStackTrace();
            }
            j++;
        }
        return bout.toByteArray();
    }
}