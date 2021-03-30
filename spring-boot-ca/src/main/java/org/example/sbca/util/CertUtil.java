package org.example.sbca.util;


import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.example.sbca.core.bc.ProviderInstance;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Date;

public class CertUtil {

    static {
        Security.addProvider(ProviderInstance.getBCProvider());
    }

    // 获取SM2椭圆曲线的参数
    private static final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");

    /**
     * 创建SM2密钥对
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static KeyPair generateSm2KeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        // 使用SM2的算法区域初始化密钥生成器
        keyPairGenerator.initialize(sm2Spec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 创建密钥对
     * @param algType
     * @param keySize
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static KeyPair genearteKeyPair(String algType, Integer keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algType, BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 生成根用户自签名证书
     * @param publicKey
     * @param privateKey
     * @param issuerDN
     * @param notBefore
     * @param notAfter
     * @param serialNumber
     * @param signAlg
     * @return
     * @throws CertificateException
     * @throws OperatorCreationException
     */
    public static X509Certificate makeSelfSignCert(PublicKey publicKey, PrivateKey privateKey, String issuerDN, Date notBefore, Date notAfter, BigInteger serialNumber, String signAlg) throws CertificateException, OperatorCreationException {

        X500Name issuer = new X500Name(issuerDN);
        //1. 创建签名
        ContentSigner signer = new JcaContentSignerBuilder(signAlg).setProvider(BouncyCastleProvider.PROVIDER_NAME).build(privateKey);
        //2. 创建证书请求
        PKCS10CertificationRequestBuilder pkcs10CertificationRequestBuilder = new JcaPKCS10CertificationRequestBuilder(issuer, publicKey);
        PKCS10CertificationRequest pkcs10CertificationRequest = pkcs10CertificationRequestBuilder.build(signer);

        //3. 创建证书
        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(issuer, serialNumber, notBefore, notAfter, pkcs10CertificationRequest.getSubject(), pkcs10CertificationRequest.getSubjectPublicKeyInfo());

        //添加扩展信息 见 X509CertExtensions

        X509CertificateHolder holder = certBuilder.build(signer);
        return new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
                .getCertificate(holder);
    }

    public static X509Certificate makeUserSignCert(PublicKey publicKey, PrivateKey caPrivateKey, String issuerDN, String subjectDN, Date notBefore, Date notAfter, BigInteger serialNumber, String signAlg) throws CertificateException, OperatorCreationException {

        X500Name issuer = new X500Name(issuerDN);
        //1. 创建签名
        ContentSigner signer = new JcaContentSignerBuilder(signAlg).setProvider(BouncyCastleProvider.PROVIDER_NAME).build(caPrivateKey);
        //2. 创建证书请求
        PKCS10CertificationRequestBuilder pkcs10CertificationRequestBuilder = new JcaPKCS10CertificationRequestBuilder(new X500Name(subjectDN), publicKey);
        PKCS10CertificationRequest pkcs10CertificationRequest = pkcs10CertificationRequestBuilder.build(signer);

        //3. 创建证书
        //SubjectPublicKeyInfo subPubKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(issuer, serialNumber, notBefore, notAfter, pkcs10CertificationRequest.getSubject(), pkcs10CertificationRequest.getSubjectPublicKeyInfo());

        //添加扩展信息 见 X509CertExtensions

        X509CertificateHolder holder = certBuilder.build(signer);
        return new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
                .getCertificate(holder);
    }

}
