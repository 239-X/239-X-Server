package com.minimal.controller.front;

import com.minimal.common.sdk.web.Response;
import com.minimal.common.sdk.web.ResponseUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @description:  图片上传mongo类
 * @Date : 2019/3/26 10:09
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */

@RestController
@RequestMapping("/mongoFile")
public class MongoFileController {

    private static final Logger logger = LoggerFactory.getLogger(MongoFileController.class);

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @RequestMapping(value = "/savePicture", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> savePicture(@RequestParam(value = "file", required = true) MultipartFile file){
        String fileName = null;
        try {
            DBObject metaData = new BasicDBObject();
            metaData.put("createdDate", new Date());
            fileName = file.getOriginalFilename()+":"+ UUID.randomUUID().toString();
            gridFsTemplate.store(file.getInputStream(), fileName,"image",metaData);
        }catch (Exception e){
            logger.error("上传mongodb图片失败",e);
            return ResponseUtils.failInServer(fileName);
        }
        return ResponseUtils.success(fileName);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getPicture(@RequestParam(value = "fileName", required = true) String fileName, HttpServletResponse httpServletResponse)throws IOException{
        OutputStream outputStream = null;
        try {
            MongoDatabase mongoDatabase =  mongoDbFactory.getDb();
            GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDatabase);

            GridFSFile result = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(fileName)));
            //FileOutputStream outputStream =new FileOutputStream("G:\\picture\\04.jpg");

            GridFSDownloadStream in = gridFSBucket.openDownloadStream(result.getObjectId());
            GridFsResource resource = new GridFsResource(result,in);
            InputStream inputStream1 = resource.getInputStream();
            byte[] imgByte = getBytes(inputStream1);
            outputStream = httpServletResponse.getOutputStream();
            outputStream.write(imgByte);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            logger.error("获取mongodb图片失败",e);
            if(outputStream != null){
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private byte[] getBytes(InputStream inputStream) throws  Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int  i = 0;
        while (-1!=(i=inputStream.read(b))){
            bos.write(b,0,i);
        }
        return bos.toByteArray();
    }
}
