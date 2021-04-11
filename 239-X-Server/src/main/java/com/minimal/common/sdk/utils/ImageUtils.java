package com.minimal.common.sdk.utils;

import com.squareup.okhttp.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

//import static com.minimal.util.TestWeChat.saveToImgByInputStream;

/**
 * @author linzhiqiang
 * @date 2019/3/24
 */
public class ImageUtils {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static String getImageBinary(String imgaeByte) {
        //这里gif动态图不可以，虽然在后面也能输出gif格式，但是却不是动图
        ByteArrayInputStream bais = new ByteArrayInputStream(imgaeByte.getBytes());
        BufferedImage bi;
        try {
            bi = ImageIO.read(bais);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void base64StringToImage(ByteArrayInputStream bais) {
        try {
//            byte[] bytes1 = decoder.decodeBuffer(base64String);
//            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            System.out.println("bais:" + bais);
            BufferedImage bi1 = ImageIO.read(bais);
            System.out.println("bi1:" + bi1);
            File w2 = new File("E://images/backImg2.jpg");//可以是jpg,png格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        base64StringToImage(getImageBinary());
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"path\": \"pages/detail/detail?id=2\"}");
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/wxa/getwxacode?access_token=19_hkzuGPJpdPNtbFzS4ptER9WTJxWhCAgtAMua6bUSWN-ib_jQLq4LHRp902vft7MTqqAEEmtCBOsgXWK8IcPvjDGYjAn-HAe-3Z7DdtZgrgOpBjim__hbsUaCTpgYfBA2RHnDAVzNQ2j5KYPTIQAbAIAVDN")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "df0a4c2b-eea7-48a8-bc91-983a375962b4")
                .build();
        Response response = client.newCall(request).execute();
        try {
            InputStream inputStream = response.body().byteStream();
            String name = "test.jpeg";
            //保存图片
//            saveToImgByInputStream(inputStream,"E:\\images",name);
//            //想命名的原文件夹的路径
//            File file1 = new File("E:\\images\test");
//            //将原文件夹更改为A，其中路径是必要的。注意
//            if(file1.exists()) {
//                System.out.println("111111111111111111");
//            }
//            file1.renameTo(new File("E:\\images\test.png"));
//            byte[] bytes =response.body().bytes();
//            System.out.println(bytes.toString());
//            File file = new File("E:/images/1234.png");
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(bytes);
//            fos.flush();
//            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getResponse() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"path\": \"pages/detail/detail?id=2\"}");
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/wxa/getwxacode?access_token=19_gl6hNmz2Q2CIPh3L8K9wy3y6BNQYukJ3yeCxqAUvrC6dAWpjdNHRoQh9QQRS0W4Ure1F_FfjSB2ZzVqi27EPOFjYGqXhcOQOuVZXhueWvb2GO5GsMVPCAoTCgpZq8gsZT4dUevKNHAEuBSGxIBZhAJATMK")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "a87165bf-5af6-4ae4-b588-940461e8db22")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().bytes().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
