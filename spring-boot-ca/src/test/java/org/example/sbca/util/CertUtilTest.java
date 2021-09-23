package org.example.sbca.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertUtilTest {

    @Test
    public void genearteKeyPairTest() throws Exception {

        KeyPair SM2KeyPair = CertUtil.generateSm2KeyPair();
        System.out.println("SM2KeyPair publicKey:  " + Base64.encodeBase64String(SM2KeyPair.getPublic().getEncoded()));
        System.out.println("SM2KeyPair privateKey:  " + Base64.encodeBase64String(SM2KeyPair.getPrivate().getEncoded()));

        KeyPair ECKeyPair = CertUtil.genearteKeyPair("EC", 256);
        System.out.println("ECKeyPair publicKey:  " + Base64.encodeBase64String(ECKeyPair.getPublic().getEncoded()));
        System.out.println("ECKeyPair privateKey:  " + Base64.encodeBase64String(ECKeyPair.getPrivate().getEncoded()));

        KeyPair RSAKeyPair = CertUtil.genearteKeyPair("RSA", 1024);
        System.out.println("RSAKeyPair publicKey:  " + Base64.encodeBase64String(RSAKeyPair.getPublic().getEncoded()));
        System.out.println("RSAKeyPair privateKey:  " + Base64.encodeBase64String(RSAKeyPair.getPrivate().getEncoded()));

    }

    @Test
    public void makeSignCertTest () throws Exception {

        KeyPair rootKeyPair = CertUtil.genearteKeyPair("EC", 256);
        String rootSignAlg = "SM3withSM2";
        System.out.println("root publicKey:  " + rootKeyPair.getPublic());
        System.out.println("root privateKey:  " + rootKeyPair.getPrivate());

        KeyPair serverKeyPair = CertUtil.genearteKeyPair("RSA", 1024);
        String serverSignAlg = "SHA256withRSA";
        System.out.println("server publicKey:  " + serverKeyPair.getPublic());
        System.out.println("server privateKey:  " + serverKeyPair.getPrivate());

        KeyPair clientKeyPair = CertUtil.genearteKeyPair("EC", 256);
        System.out.println("client publicKey:  " + clientKeyPair.getPublic());
        System.out.println("client privateKey:  " + clientKeyPair.getPrivate());

        String issuerDN = "C=CN, ST=北京, L=北京, O=亚信, OU=亚信, CN=RootCA, E=example@gmail.com";
        Date notBefore = new Date();
        Date notAfter = new Date(System.currentTimeMillis() + 1000 * 86400 * 365L);
        X509Certificate rootCert = CertUtil.makeSelfSignCert(rootKeyPair.getPublic(), rootKeyPair.getPrivate(), issuerDN, notBefore, notAfter, BigInteger.valueOf(1L), rootSignAlg);
        System.out.println("rootIssuserDN:  " + rootCert.getIssuerDN() + "  rootSubjectDN  " + rootCert.getSubjectDN());
        System.out.println("rootCert publicKey:  " + rootCert.getPublicKey());

        String serverSubjectDN = "C=CN, ST=河北, L=邯郸, O=河北工程大学, OU=河北工程大学, CN=UserCA, E=example@gmail.com";
        X509Certificate userCert = CertUtil.makeUserSignCert(serverKeyPair.getPublic(), rootKeyPair.getPrivate(), rootCert.getSubjectDN().getName(), serverSubjectDN, notBefore, notAfter, BigInteger.valueOf(1L), rootSignAlg);
        System.out.println("serverIssuserDN:  " + userCert.getIssuerDN() + "  serverSubjectDN  " + userCert.getSubjectDN());
        System.out.println("serverCert publicKey:  " + userCert.getPublicKey());

        String clientSubject1DN = "C=CN, ST=吉林, L=桦甸, O=第八中学, OU=第八中学, CN=UserCA, E=example@gmail.com";
        X509Certificate clientCert = CertUtil.makeUserSignCert(clientKeyPair.getPublic(), serverKeyPair.getPrivate(), userCert.getSubjectDN().getName(), clientSubject1DN, notBefore, notAfter, BigInteger.valueOf(1L), serverSignAlg);
        System.out.println("clientIssuserDN:  " + clientCert.getIssuerDN() + "  clientSubjectDN  " + clientCert.getSubjectDN());

    }

}
