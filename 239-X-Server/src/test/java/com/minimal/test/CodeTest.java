package com.minimal.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by administrator on 2019/12/12.
 */
public class CodeTest {
    public static void main(String[] args) throws Exception {
        String str = "rO0ABXNyAB9jb20uYWxpYmFiYS5mYXN0anNvbi5KU09OT2JqZWN0AAAAAAAAAAECAAFMAANtYXB0AA9MamF2YS91dGlsL01hcDt4cHNyABFqYXZhLnV0aWwu\n" +
                "SGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAIdAAKcmVwb3J0VHlwZXQADVJlYWxUaW1lT3Jk\n" +
                "ZXJ0AAlhY2NvdW50SWR0AAQ1MDg4dAAHaXNGaXJzdHQAATB0AAhzaXRlQ29kZXQAAnVzdAAIc2F2ZURhdGV0ABMyMDE5LTEyLTEyIDE4OjQzOjQ3dAAHZW5k\n" +
                "RGF0ZXQAEzIwMTktMTItMTIgMTA6NDM6NDd0AAZ1c2VySWR0AAQ1NjMzdAAJc3RhcnREYXRldAATMjAxOS0xMi0xMiAxMDo0Mzo0N3g=";
//        base64(str);
        enAndDeCode(str);
    }

    /**
     * Base64
     *
     */
    public static void base64(String str) {
//        byte[] bytes = str.getBytes();

        //Base64 加密
//        String encoded = Base64.getEncoder().encodeToString(bytes);
//        System.out.println("Base 64 加密后：" + encoded);

        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(str);

        String decodeStr = new String(decoded);
        System.out.println("Base 64 解密后：" + decodeStr);

        System.out.println();


    }

    /**
     * BASE64加密解密
     */
    public static void enAndDeCode(String str) throws Exception {
//        String data = encryptBASE64(str.getBytes());
//        System.out.println("sun.misc.BASE64 加密后：" + data);

        byte[] byteArray = decryptBASE64(str);
        System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray,"utf-8"));
        System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray,"GB2312"));
        System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray,"ISO-8859-1"));
        System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray,"GBK"));
        System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray,"UTF-16"));
    }

    /**
     * BASE64解密
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}