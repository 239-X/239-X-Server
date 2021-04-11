package com.minimal.common.sdk.utils;

import java.io.*;

/**
 * 序列化工具
 * @author WuBin
 *
 */
public class SerializeUtils {

	/**
	 * 对象序列化成字节数组
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(Object obj) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			return baos.toByteArray();
		}
	}
	
	/**
	 * 字节数组反序列化成对象
	 * @param buff
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] buff) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buff))) {
			return (T) ois.readObject();
		}
	}
	
}
