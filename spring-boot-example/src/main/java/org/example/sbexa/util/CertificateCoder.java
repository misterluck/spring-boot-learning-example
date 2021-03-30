package org.example.sbexa.util;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateCoder {

    /****
     * 获得私钥，获得私钥后，通过RSA算方法实现进行"私钥加密，公钥解密"和"公钥加密，私钥解密"操作
     * @param keyStorePath 密钥库路径
     * @param alias 别名
     * @param keystore_password 秘钥库密码
     * @param ca_password 证书密码
     * @return  私钥
     */
    private static PrivateKey getPrivateKeyByKeyStore(String keyStorePath, String alias, String keystore_password, String ca_password)throws Exception{
        //获得密钥库
        KeyStore ks = getKeyStore(keyStorePath,keystore_password);
        //获得私钥
        return  (PrivateKey)ks.getKey(alias, ca_password.toCharArray());

    }

    /****
     * 由Certificate获得公钥，获得公钥后，通过RSA算方法实现进行"私钥加密，公钥解密"和"公钥加密，私钥解密"操作
     * @param certificatePath  证书路径
     * @return 公钥
     */
    private static PublicKey getPublicKeyByCertificate(String certificatePath)throws Exception {
        //获得证书
        Certificate certificate = getCertificate(certificatePath);
        //获得公钥
        return certificate.getPublicKey();
    }

    /****
     * 加载数字证书，JAVA 6仅支持x.509的数字证书
     * @param certificatePath  证书路径
     * @return   证书
     * @throws Exception
     */
    private static Certificate getCertificate(String certificatePath) throws Exception{
        //实例化证书工厂
        CertificateFactory certificateFactory = CertificateFactory.getInstance("x.509");
        //取得证书文件流
        FileInputStream in = new FileInputStream(certificatePath);
        //生成证书
        Certificate certificate = certificateFactory.generateCertificate(in);
        //关闭证书文件流
        in.close();
        return certificate;
    }

    /****
     * 获得Certificate
     * @param keyStorePath 密钥库路径
     * @param alias 别名
     * @param keystore_password  秘钥库密码
     * @return  证书
     * @throws Exception
     */
    private static Certificate getCertificate(String keyStorePath,String alias,String keystore_password) throws Exception{
        //由密钥库获得数字证书构建数字签名对象
        //获得密钥库
        KeyStore ks = getKeyStore(keyStorePath,keystore_password);
        //获得证书
        return ks.getCertificate(alias);
    }

    /****
     * 加载密钥库，加载了以后，我们就能通过相应的方法获得私钥，也可以获得数字证书
     * @param keyStorePath 密钥库路径
     * @param keystore_password 密码
     * @return  密钥库
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath,String keystore_password) throws Exception{
        //实例化密钥库
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        //获得密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        //加载密钥库
        ks.load(is,keystore_password.toCharArray());
        //关闭密钥库文件流
        is.close();
        return ks;
    }

    /****
     * 私钥加密
     * @param data  待加密的数据
     * @param keyStorePath  密钥库路径
     * @param alias  别名
     * @param keystore_password   秘钥库密码
     * @param ca_password   证书密码
     * @return  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPriateKey(byte[] data,String keyStorePath,String alias,String keystore_password,String ca_password) throws Exception{
        //获得私钥
        PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath,alias,keystore_password,ca_password);
        //对数据加密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }

    /****
     * 私钥解密
     * @param data  待解密数据
     * @param keyStorePath 密钥库路径
     * @param alias  别名
     * @param keystore_password   秘钥库密码
     * @param ca_password   证书密码
     * @return  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,String keyStorePath,String alias,String keystore_password,String ca_password) throws Exception{
        //取得私钥
        PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath,alias,keystore_password,ca_password);
        //对数据解密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }

    /****
     * 公钥加密
     * @param data  等待加密数据
     * @param certificatePath  证书路径
     * @return   加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,String certificatePath) throws Exception{
        //取得公钥
        PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
        //对数据加密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /****
     * 公钥解密
     * @param data  等待解密的数据
     * @param certificatePath  证书路径
     * @return  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data,String certificatePath)throws Exception{
        //取得公钥
        PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
        //对数据解密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /****
     * @param sign  签名
     * @param keyStorePath 密钥库路径
     * @param alias 别名
     * @param keystore_password   秘钥库密码
     * @param ca_password   证书密码
     * @return 签名
     * @throws Exception
     */
    public static byte[] sign(byte[] sign,String keyStorePath,String alias,String keystore_password,String ca_password)throws Exception{
        //获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath,alias,keystore_password);
        //构建签名,由证书指定签名算法
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        //获取私钥
        PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath,alias,keystore_password,ca_password);
        //初始化签名，由私钥构建
        signature.initSign(privateKey);
        signature.update(sign);
        return signature.sign();
    }


    /****
     * 验证签名
     * @param data  数据
     * @param sign  签名
     * @param certificatePath  证书路径
     * @return  验证通过为真
     * @throws Exception
     */
    public static boolean verify(byte[] data,byte[] sign,String certificatePath) throws Exception{
        //获得证书
        X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
        //由证书构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        //由证书初始化签名，实际上是使用了证书中的公钥
        signature.initVerify(x509Certificate);
        signature.update(data);
        return signature.verify(sign);
    }


}
