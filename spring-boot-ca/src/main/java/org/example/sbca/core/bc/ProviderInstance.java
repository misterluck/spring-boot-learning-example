package org.example.sbca.core.bc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;

/**
 * 提供者创建工具
 * @author 赵雷
 * 2021年3月11日 下午16:58:00
 */
public class ProviderInstance {

    private static Provider BCProvider;

    public static Provider getBCProvider() {
        if (null == BCProvider) {
            synchronized (ProviderInstance.class) {
                if (null == BCProvider) {
                    BCProvider = new BouncyCastleProvider();
                }
            }
        }
        return BCProvider;
    }

}
