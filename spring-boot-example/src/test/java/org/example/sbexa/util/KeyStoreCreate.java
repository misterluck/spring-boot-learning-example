package org.example.sbexa.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

/**
 * 主要用于创建keyStore文件，保存起来
 *
 */
public class KeyStoreCreate {

    public static String filePath = "F:\\Certifacate\\cert.keystore";
    private static final int keysize = 1024;
    // 通用名
    private static final String commonName = "www.ctbri.com";
    // 组织单位名称
    private static final String organizationalUnit = "IT";
    // 组织名称
    private static final String organization = "test";
    // 城市或地区名称
    private static final String city = "beijing";
    // 省/市/自治区名称
    private static final String state = "beijing";
    // 国家/地区代码
    private static final String country = "beijing";

    private static final long validity = 1096; // 3 years
    private static final String alias = "tomcat";
    private static final char[] keyPassword = "123456".toCharArray();

    public static void main(String[] args) throws GeneralSecurityException {
        try {

            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(null, null);

            CertAndKeyGen keypair = new CertAndKeyGen("RSA", "SHA1WithRSA", null);
            X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);

            keypair.generate(keysize);

            PrivateKey privateKey = keypair.getPrivateKey();
            X509Certificate[] chain = new X509Certificate[1];
            chain[0] = keypair.getSelfCertificate(x500Name, new Date(), (long)validity*24*60*60);

            // store away the key store
            FileOutputStream fos = new FileOutputStream(filePath);
            ks.setKeyEntry(alias, privateKey, keyPassword, chain);
            ks.store(fos, keyPassword);
            fos.close();

            Certificate certificate = ks.getCertificate(alias);


            System.out.println("create Success");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
