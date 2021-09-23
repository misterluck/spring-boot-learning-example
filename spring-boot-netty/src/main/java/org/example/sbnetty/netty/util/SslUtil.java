package org.example.sbnetty.netty.util;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

public class SslUtil {
    private static SelfSignedCertificate ssc = null;
    private static SslContext serverSslCtx = null;
    private static SslContext clientSslCtx = null;

    static {
        try {
            ssc = new SelfSignedCertificate();
            serverSslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            clientSslCtx = SslContextBuilder.forServer(ssc.key(), ssc.cert()).build();
            // clientSslCtx = SslContextBuilder.forClient().build();
        } catch (CertificateException | SSLException e) {
            e.printStackTrace();
        }
    }

    public static SslContext getServerSslCtx() {
        return serverSslCtx;
    }

    public static SslContext getClientSslCtx() {
        return clientSslCtx;
    }

}
