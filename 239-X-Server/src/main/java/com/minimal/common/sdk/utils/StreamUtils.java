package com.minimal.common.sdk.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * IO流工具
 * @author wubin
 *
 */
public class StreamUtils {

    /**
     * 流转成String
     * @param in
     * @return
     * @throws IOException
     */
	public static String streamToString(InputStream in) throws IOException {  
        StringBuffer out = new StringBuffer();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            out.append(new String(b, 0, n));  
        }  
        return out.toString();  
    }
}
