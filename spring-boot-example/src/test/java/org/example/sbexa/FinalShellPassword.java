package org.example.sbexa;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

public class FinalShellPassword {
    //    private static final String OO0O00OO00OOOOOO0000O0O000O0O0000OOOO00OO000OO0000OO000000O00O0O000OO00O0O000O00O0O00OOOOOO0OOO0 = "DES";
    public static long number = 3680984568597093857L;
    private static int num = 8;



    public static void main(String[] args) throws Exception {
//        String content = "sdsfew1tf45r1g3";
//        String s1 = encode(content);
        // 加密后的密码
        String s1 = "e2lrLAwsJ0PD126vZFsPXIDUKmQXEi5H";
        String s2 = decode(s1);
        System.out.println(s1);
        System.out.println(s2);
    }

    public static String decode(String data) throws IOException, Exception {
        if (data == null) {
            return null;
        } else {
            String rs = "";
            if (!fff(data)) {
                byte[] buf = ggg(data);
                byte[] head = new byte[num];
                System.arraycopy(buf, 0, head, 0, head.length);
                byte[] d = new byte[buf.length - head.length];
                System.arraycopy(buf, head.length, d, 0, d.length);
                byte[] bt = jjj(d, kkk(head));
                rs = new String(bt);
            }

            return rs;
        }
    }

    public static String encode(String content) throws Exception {
        byte[] head = aaa(num);
        byte[] d = bbb(content.getBytes("utf-8"), head);
        byte[] result = new byte[head.length + d.length];
        System.arraycopy(head, 0, result, 0, head.length);
        System.arraycopy(d, 0, result, head.length, d.length);
        String rs = ccc(result);
        return rs;
    }

    static byte[] aaa(int len) {
        byte[] data = new byte[len];

        for(int i = 0; i < len; ++i) {
            data[i] = (byte)(new Random()).nextInt(127);
        }

        return data;
    }


    public static byte[] bbb(byte[] data, byte[] head) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(kkk(head));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    public static String ccc(byte[] byteData) throws UnsupportedEncodingException {
        return ddd(byteData, "UTF-8");
    }

    public static String ddd(byte[] byteData, String encoding) throws UnsupportedEncodingException {
        if (byteData == null) {
            throw new IllegalArgumentException("byteData cannot be null");
        } else {
            return new String(eee(byteData), encoding);
        }
    }
    public static final byte[] eee(byte[] byteData) {
        if (byteData == null) {
            throw new IllegalArgumentException("byteData cannot be null");
        } else {
            byte[] byteDest = new byte[(byteData.length + 2) / 3 * 4];
            int iSrcIdx = 0;

            int iDestIdx;
            for(iDestIdx = 0; iSrcIdx < byteData.length - 2; iSrcIdx += 3) {
                byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx] >>> 2 & 63);
                byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx + 1] >>> 4 & 15 | byteData[iSrcIdx] << 4 & 63);
                byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx + 2] >>> 6 & 3 | byteData[iSrcIdx + 1] << 2 & 63);
                byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx + 2] & 63);
            }

            if (iSrcIdx < byteData.length) {
                byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx] >>> 2 & 63);
                if (iSrcIdx < byteData.length - 1) {
                    byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx + 1] >>> 4 & 15 | byteData[iSrcIdx] << 4 & 63);
                    byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx + 1] << 2 & 63);
                } else {
                    byteDest[iDestIdx++] = (byte)(byteData[iSrcIdx] << 4 & 63);
                }
            }

            for(iSrcIdx = 0; iSrcIdx < iDestIdx; ++iSrcIdx) {
                if (byteDest[iSrcIdx] < 26) {
                    byteDest[iSrcIdx] = (byte)(byteDest[iSrcIdx] + 65);
                } else if (byteDest[iSrcIdx] < 52) {
                    byteDest[iSrcIdx] = (byte)(byteDest[iSrcIdx] + 97 - 26);
                } else if (byteDest[iSrcIdx] < 62) {
                    byteDest[iSrcIdx] = (byte)(byteDest[iSrcIdx] + 48 - 52);
                } else if (byteDest[iSrcIdx] < 63) {
                    byteDest[iSrcIdx] = 43;
                } else {
                    byteDest[iSrcIdx] = 47;
                }
            }

            while(iSrcIdx < byteDest.length) {
                byteDest[iSrcIdx] = 61;
                ++iSrcIdx;
            }

            return byteDest;
        }
    }
    public static boolean fff(String str) {
        if (str == null) {
            return true;
        } else {
            String s2 = str.trim();
            return s2.equals("");
        }
    }
    public static final byte[] ggg(String encoded) throws UnsupportedEncodingException {
        return hhh(encoded, "UTF-8");
    }
    public static final byte[] hhh(String encoded, String encoding) throws IllegalArgumentException, UnsupportedEncodingException {
        if (encoded == null) {
            throw new IllegalArgumentException("encoded cannot be null");
        } else {
            return iii(encoded.getBytes(encoding));
        }
    }
    public static final byte[] iii(byte[] byteData) throws IllegalArgumentException {
        if (byteData == null) {
            throw new IllegalArgumentException("byteData cannot be null");
        } else {
            byte[] byteTemp = new byte[byteData.length];

            int reviSrcIdx;
            for(reviSrcIdx = byteData.length; reviSrcIdx - 1 > 0 && byteData[reviSrcIdx - 1] == 61; --reviSrcIdx) {
            }

            if (reviSrcIdx - 1 == 0) {
                return null;
            } else {
                byte[] byteDest = new byte[reviSrcIdx * 3 / 4];

                int iSrcIdx;
                for(iSrcIdx = 0; iSrcIdx < reviSrcIdx; ++iSrcIdx) {
                    if (byteData[iSrcIdx] == 43) {
                        byteTemp[iSrcIdx] = 62;
                    } else if (byteData[iSrcIdx] == 47) {
                        byteTemp[iSrcIdx] = 63;
                    } else if (byteData[iSrcIdx] < 58) {
                        byteTemp[iSrcIdx] = (byte)(byteData[iSrcIdx] + 52 - 48);
                    } else if (byteData[iSrcIdx] < 91) {
                        byteTemp[iSrcIdx] = (byte)(byteData[iSrcIdx] - 65);
                    } else if (byteData[iSrcIdx] < 123) {
                        byteTemp[iSrcIdx] = (byte)(byteData[iSrcIdx] + 26 - 97);
                    }
                }

                iSrcIdx = 0;

                int iDestIdx;
                for(iDestIdx = 0; iSrcIdx < reviSrcIdx && iDestIdx < byteDest.length / 3 * 3; iSrcIdx += 4) {
                    byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx] << 2 & 252 | byteTemp[iSrcIdx + 1] >>> 4 & 3);
                    byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx + 1] << 4 & 240 | byteTemp[iSrcIdx + 2] >>> 2 & 15);
                    byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx + 2] << 6 & 192 | byteTemp[iSrcIdx + 3] & 63);
                }

                if (iSrcIdx < reviSrcIdx) {
                    if (iSrcIdx < reviSrcIdx - 2) {
                        byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx] << 2 & 252 | byteTemp[iSrcIdx + 1] >>> 4 & 3);
                        byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx + 1] << 4 & 240 | byteTemp[iSrcIdx + 2] >>> 2 & 15);
                    } else {
                        if (iSrcIdx >= reviSrcIdx - 1) {
                            throw new IllegalArgumentException("Warning: 1 input bytes left to process. This was not Base64 input");
                        }

                        byteDest[iDestIdx++] = (byte)(byteTemp[iSrcIdx] << 2 & 252 | byteTemp[iSrcIdx + 1] >>> 4 & 3);
                    }
                }

                return byteDest;
            }
        }
    }
    public static byte[] jjj(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }

    static byte[] kkk(byte[] head) {
        long ks = number / (long)(new Random((long)head[5])).nextInt(127);
        Random random = new Random(ks);
        int t = head[0];

        for(int i = 0; i < t; ++i) {
            random.nextLong();
        }

        long n = random.nextLong();
        Random r2 = new Random(n);
        long[] ld = new long[]{(long)head[4], r2.nextLong(), (long)head[7], (long)head[3], r2.nextLong(), (long)head[1], random.nextLong(), (long)head[2]};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        long[] var15 = ld;
        int var14 = ld.length;

        for(int var13 = 0; var13 < var14; ++var13) {
            long l = var15[var13];

            try {
                dos.writeLong(l);
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }

        try {
            dos.close();
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        byte[] keyData = bos.toByteArray();
        keyData = DigestUtils.md5(keyData);
        return keyData;
    }
}

