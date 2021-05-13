package com.minimal.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛云工具类
 * @author linzhiqiang
 */
@Slf4j
public class QiniuUtil {
    /**
     * 开发者kek
     */
    private static String accessKey = "C9ubAHfu5H2SlIwCOL6raPbitJvtRSdo8SevzMrE";

    /**
     * 开发者密钥
     */
    private static String secretKey = "pNqp5fIdtAY64UMy83La6IcY99V80zO66Dzq2fXh";

    /**
     * 外链默认域名
     */
    private static String domainOfBucket = "pic.wolzq.com";

    /**
     * 模块空间
     */
    public static final String DEFAULT_BUCKET = "239-blogs-pic";

    public static String upload(String bucket, String key, FileInputStream fileInputStream) throws QiniuException {
        InputStream inputStream = new GZipInputStreamDeflater(fileInputStream);
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(StringUtils.isBlank(bucket) ? DEFAULT_BUCKET : bucket);
        Response response = uploadManager.put(inputStream, key, upToken, null, null);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }

    public static String upload(String bucket, String key, String filePath) throws QiniuException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(StringUtils.isBlank(bucket) ? DEFAULT_BUCKET : bucket);
        Response response = uploadManager.put(filePath, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }


    public static String upload(String bucket, String key, byte[] uploadBytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        //String key = null;
        //byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("=====> " + r.toString());
            try {
                log.error("=====> " + r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public static String getDownloadUrl(String key) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(key, "UTF-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;    //1小时，可以自定义链接过期时间
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    public static void remove(String bucket, String key) throws QiniuException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //try {
        bucketManager.delete(bucket, key);
        //} catch (QiniuException ex) {
        //如果遇到异常，说明删除失败
        //log.error("=====> " + ex.code());
        //log.error("=====> " + ex.response.toString());
        //}
    }

    public static void main(String[] args) throws IOException {
//        InputStream is = new FileInputStream("F:\\test.png");
//        MockMultipartFile mock = new MockMultipartFile("test.png", is);
//        upload("enableseller", "test.png", mock.getBytes());
        //download();
        //remove("enableseller-pub","eefdfddf-a283-4370-b08b-ff2e1c94e8be.txt");
        log.info("=====> " + getDownloadUrl("test.png"));
//        RestTemplate rest = new RestTemplate();
//        ResponseEntity<Resource> entity = rest.postForEntity(getDownloadUrl("73695f12-5618-4ca3-b7d0-cb365701b4c6.pdf"), null, Resource.class);
//        InputStream input = entity.getBody().getInputStream();
//        int index;
//        byte[] bytes = new byte[1024];
//        FileOutputStream downloadFile = new FileOutputStream("D:\\73695f12-5618-4ca3-b7d0-cb365701b4c6.pdf");
//        while ((index = input.read(bytes)) != -1) {
//            downloadFile.write(bytes, 0, index);
//            downloadFile.flush();
//        }
//        downloadFile.close();
//        input.close();
    }

    /**
     * 覆盖上传
     *
     * @param bucket
     * @param key
     * @param uploadBytes
     * @return
     */
    public static String overrideUpload(String bucket, String key, byte[] uploadBytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        //String key = null;
        //byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key, 3600, new StringMap().put("insertOnly", 0));
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("=====> " + r.toString());
            try {
                log.error("=====> " + r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }
}
