package com.minimal.controller.front;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimal.common.api.dto.*;
import com.minimal.common.sdk.log.RequestLog;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.common.sdk.web.Response;
import com.minimal.common.sdk.web.ResponseUtils;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.service.ArticleService;
import com.minimal.service.ConfigService;
import com.minimal.service.UserService;
import com.minimal.service.*;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序接口
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class AppletController {
    private Logger logger = LoggerFactory.getLogger(AppletController.class);
    private static final String LOG_TITLE = "【文章信息查询】";

    private final static String appId = "wx3d887bdddb6c1fcf";
    private final static String secret = "d3554322c7b964ec2bcd2ce646de2a28";
    private final static String authorizationCode = "authorization_code";
    private final static String clientCredential = "client_credential";

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.imgPath}")
    private String imgPath;

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private HomeModuleService homeModuleService;

    @Autowired
    private ArticleSubjectService articleSubjectService;

    @Autowired
    private ArticleCollectService articleCollectService;
    /**
     * 获取文章列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "homeArticles", method = RequestMethod.GET)
    public String homeArticles(int pageNo, int pageSize) {
        String result = null;
        try {
            Map<String, Object> map = articleService.selectAll(pageNo, pageSize);
            result = JsonUtils.toJson(map);
        } catch (Exception e) {
            logger.error("{}获取文章列表信息失败,pageNo={},pageSize={}", LOG_TITLE, pageNo, pageSize, e);
//            return ResponseUtils.failInServer(result);
        }
//        return ResponseUtils.success(result);
        return result;
    }

    /**
     * 通过文章id查询文章详情
     *
     * @param articleId
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "articleDetail", method = RequestMethod.GET)
    public String articleDetail(String articleId, String subjectId) {
        String result = null;
        try {
            Map<String, Object> map = articleService.selectById(articleId, subjectId);
            result = JsonUtils.toJson(map);
        } catch (Exception e) {
            logger.error("{}获取文章详情失败,articleId={}", LOG_TITLE, articleId, e);
//            return ResponseUtils.failInServer(result);
        }
//        return ResponseUtils.success(result);
        return result;
    }

    /**
     * 获取所有配置信息
     *
     * @return
     */
    @RequestMapping(value = "selectAllConfig", method = RequestMethod.GET)
    public String selectAllConfig() {
        String result = null;
        try {
            Map<String, Object> map = configService.selectAll();
            result = JsonUtils.toJson(map);
        } catch (Exception e) {
            logger.error("{}获取所有配置信息失败", LOG_TITLE, e);
//            return ResponseUtils.failInServer(result);
        }
//        return ResponseUtils.success(result);
        return result;
    }

    @RequestMapping(value = "deleteAllCache", method = RequestMethod.GET)
    public Response<Boolean> deleteAllCache() {
        try {
            String keys = "minimal:";
            redisUtil.delLikeKeys(keys);
        } catch (Exception e) {
            return ResponseUtils.failInServer(false);
        }
        return ResponseUtils.success(true);
    }

    /**
     * 查询指定key的配置值
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "selectConfigByKey", method = RequestMethod.GET)
    public String selectConfigByKey(String key) {
        return JsonUtils.toJson(configService.selectConfigByKey(key));
    }

    /**
     * 获取微信的openId
     *
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "selectJscode2session", method = RequestMethod.POST)
    public String selectJscode2session(@RequestBody WeChatLoginDto weChatLoginDto) {
        logger.info("jsCode:" + weChatLoginDto.getJsCode());
        String jsonResult = weChatService.getJscode2session(appId, secret, weChatLoginDto.getJsCode(), authorizationCode);
        logger.info("jsonResult:" + jsonResult);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "200");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String openid = rootNode.path("openid").asText();
        logger.info("openid:" + openid);
        weChatLoginDto.setOpenId(openid);
        userService.loginWeChatUser(weChatLoginDto);
        response.put("openid", openid);
        if(StringUtils.isBlank(openid)){
            response.put("status", "500");
        }
        return JsonUtils.toJson(response);
    }

    /**
     * 用户点赞
     *
     * @param userLikeDto
     * @return
     */
    @RequestMapping(value = "userLike", method = RequestMethod.POST)
    public String userLike(@RequestBody UserLikeDto userLikeDto) {
        return JsonUtils.toJson(userService.articleLike(userLikeDto));
    }

    /**
     * 用户收藏
     *
     * @param userLikeDto
     * @return
     */
    @RequestMapping(value = "userCollect", method = RequestMethod.POST)
    public String userCollect(@RequestBody UserLikeDto userLikeDto) {
        return JsonUtils.toJson(articleCollectService.userCollect(userLikeDto));
    }

    /**
     * 用户发布评论
     *
     * @param articleCommentDto
     * @return
     */
    @RequestMapping(value = "postComment", method = RequestMethod.POST)
    public String postComment(@RequestBody ArticleCommentDto articleCommentDto) {
        return JsonUtils.toJson(articleCommentService.insertArticleComment(articleCommentDto));
    }

    /**
     * 查询所有评论
     *
     * @param articleId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectArticleComments", method = RequestMethod.GET)
    public String selectArticleComments(String articleId, int pageNo, int pageSize) {
        return JsonUtils.toJson(articleCommentService.selectArticleCommentsByArticleId(articleId, pageNo, pageSize));
    }

    /**
     * 查询所有的banner列表
     *
     * @return
     */
    @RequestMapping(value = "selectBanners", method = RequestMethod.GET)
    public String selectBanners() {
        return JsonUtils.toJson(bannerService.selectBanners());
    }

    /**
     * 海报生成
     *
     * @param httpServletResponse
     * @param path
     * @param articleId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "getwxacode", method = RequestMethod.GET)
    public String getwxacode(HttpServletResponse httpServletResponse, String path, String articleId) throws IOException {
        String accessTokenJson = weChatService.token(clientCredential, appId, secret);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(accessTokenJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String accessToken = rootNode.path("access_token").asText();
        WXACodeDto wXACodeDto = new WXACodeDto();
        wXACodeDto.setPath(path);
        OkHttpClient client = new OkHttpClient();
        com.squareup.okhttp.MediaType mediaType = com.squareup.okhttp.MediaType.parse("application/json");
        com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, JsonUtils.toJson(wXACodeDto));
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + accessToken)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        com.squareup.okhttp.Response response = client.newCall(request).execute();
        String imgName = articleId + ".jpeg";
        InputStream inputStream = response.body().byteStream();
        String pathImg = imgPath + saveToImgByInputStream(inputStream, uploadFolder, imgName);
        return pathImg;
    }

    /**
     * 将二进制转换成文件保存
     *
     * @param instreams 二进制流
     * @param imgPath   图片的保存路径
     * @param imgName   图片的名称
     * @return 1：保存正常
     * 0：保存失败
     */
    public static String saveToImgByInputStream(InputStream instreams, String imgPath, String imgName) {
        int stateInt = 1;
        if (instreams != null) {
            try {
                //可以是任何图片格式.jpg,.png等
                File file = new File(imgPath, imgName);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return imgName;
    }

    /**
     * 获取首页猜你喜欢
     *
     * @return
     */
    @RequestMapping(value = "selectAllHomeModule", method = RequestMethod.GET)
    public String selectAllHomeModule() {
        return JsonUtils.toJson(homeModuleService.selectAllHomeModule());
    }

    /**
     * 获取专题列表信息
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectAllArticleSubject", method = RequestMethod.GET)
    public String selectAllArticleSubject(String openId) {
        return JsonUtils.toJson(articleSubjectService.selectAllArticleSubject(openId));
    }


    /**
     * 查询我的订阅
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectSubjectByOpenId", method = RequestMethod.GET)
    public String selectSubjectByOpenId(String openId) {
        return JsonUtils.toJson(articleSubjectService.selectSubjectByOpenId(openId));
    }

    /**
     * 查询我的推荐关系
     *
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectRelationshipByOpenId", method = RequestMethod.GET)
    public String selectRelationshipByOpenId(String openId,int pageNo, int pageSize) {
        return JsonUtils.toJson(userService.selectRelationshipByOpenId(openId,pageNo,pageSize));
    }
    /**
     * 查询指定主题的所有文章列表
     *
     * @param id
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectArticleBySubjectId", method = RequestMethod.GET)
    public String selectArticleBySubjectId(String id, String key, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isNotBlank(id) && !"0".equals(id)) {
            result = articleSubjectService.selectArticleBySubjectId(id, pageNo, pageSize);
        } else if (StringUtils.isNotBlank(key)) {
            result = articleService.selectByKey(key, pageNo, pageSize);
        }
        return JsonUtils.toJson(result);
    }

    /**
     * 获取指定id专题信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectArticleSubjectById", method = RequestMethod.GET)
    public String selectArticleSubjectById(String id, String openId) {
        Map<String, Object> result = articleSubjectService.selectArticleSubjectById(id, openId);
        return JsonUtils.toJson(result);
    }

    /**
     * 获取某一类型文章（热门列表）
     *
     * @param typeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectHotArticleByTypeId", method = RequestMethod.GET)
    public String selectHotArticleByTypeId(String typeId, int pageNo, int pageSize) {
        Map<String, Object> result = articleService.selectHotArticleByTypeId(typeId, pageNo, pageSize);
        return JsonUtils.toJson(result);
    }

    /**
     * 查询我的收藏
     *
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectCollectByOpenId", method = RequestMethod.GET)
    public String selectCollectByOpenId(String openId, int pageNo, int pageSize) {
        Map<String, Object> result = articleCollectService.selectCollectByOpenId(openId, pageNo, pageSize);
        return JsonUtils.toJson(result);
    }

    /**
     * 每日签到
     *
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "everydaySign", method = RequestMethod.POST)
    public String everydaySign(@RequestBody WeChatLoginDto weChatLoginDto) {
        Map<String, Object> result = userService.everydaySign(weChatLoginDto);
        return JsonUtils.toJson(result);
    }

    /**
     * 通过openId获取用户信息
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectUserByOpenId", method = RequestMethod.GET)
    public String selectUserByOpenId(String openId) {
        Map<String, Object> result = userService.selectUserByOpenId(openId);
        return JsonUtils.toJson(result);
    }

    /**
     * 用户订阅专题
     *
     * @param articleSubjectDto
     * @return
     */
    @RequestMapping(value = "subjectSubscribeById", method = RequestMethod.POST)
    public String subjectSubscribeById(@RequestBody ArticleSubjectDto articleSubjectDto) {
        Map<String, Object> result = articleSubjectService.subjectSubscribeById(articleSubjectDto);
        return JsonUtils.toJson(result);
    }

    /**
     * 取消专题订阅
     *
     * @param articleSubjectDto
     * @return
     */
    @RequestMapping(value = "cancelSubject", method = RequestMethod.POST)
    public String cancelSubject(@RequestBody ArticleSubjectDto articleSubjectDto) {
        Map<String, Object> result = articleSubjectService.cancelSubject(articleSubjectDto);
        return JsonUtils.toJson(result);
    }

    /**
     * request测试专用
     * @return
     */
    @RequestLog(module = "requestTest", operationDesc = "request测试专用")
    @RequestMapping(value = "requestTest", method = RequestMethod.POST)
    public String requestTest(@RequestBody ArticleSubjectDto articleSubjectDto) {
        String result = null;
        try {
            System.out.println("我的是方法");
            result = "请求成功";
        }catch (Exception e){
            logger.error("requestTest查询失败", e);
            return JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }

//    /**
//     * thread测试专用
//     * @return
//     */
//    @RequestMapping(value = "threadTest", method = RequestMethod.GET)
//    public String threadTest() {
//        String result = null;
//        try {
//            result = "我是Thread测试";
//            for(int i=0;i<2000;i++){
//                Thread t1 = new TestThread("t"+i);
//                t1.start();
//            }
//        }catch (Exception e){
//            logger.error("requestTest查询失败", e);
//            return JsonUtils.toJson(ResponseUtils.failInServer(result));
//        }
//        return JsonUtils.toJson(ResponseUtils.success(result));
//    }
}

