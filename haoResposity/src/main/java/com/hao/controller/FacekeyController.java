package com.hao.controller;


import com.alibaba.fastjson.JSON;
import com.hao.entity.UserInf;
import com.hao.service.impl.UserInfServiceImpl;
import com.hao.utils.Base64Util;
import com.hao.utils.FileUtil;
import com.hao.utils.GsonUtils;
import com.hao.utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// 人脸对比 示例代码
@Controller
public class FacekeyController {


    @Autowired
    UserInfServiceImpl userInfService;
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "G9SzUr4YWN6gdn7GM17ZpMN2";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "71dDdREN7bsRd6zGseril0IREuMmg3cc";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceMatch(String base64,String cgBase64) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";

        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        map.put("image",base64);
        map.put("image_type","BASE64");
        HashMap map1 = new HashMap();
        map1.put("image",cgBase64);
        map1.put("image_type","BASE64");
        arrayList.add(map);
        arrayList.add(map1);

        String param = GsonUtils.toJson(arrayList);

        try {
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    
    public static void main(String[] args) {
        String realPath = "C:/Users/10986/Desktop/ssm/target/ssm-1.0-SNAPSHOT/uploadFace/1.png";
        byte[] bytes = new byte[0];
        try {
            bytes = FileUtil.readFileByBytes(realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = Base64Util.encode(bytes);
        String realPath1 = "C:/Users/10986/Desktop/ssm/target/ssm-1.0-SNAPSHOT/uploadFace/2.png";
        byte[] bytes1 = new byte[0];
        try {
            bytes1 = FileUtil.readFileByBytes(realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode1 = Base64Util.encode(bytes);

        String s = faceMatch(encode, encode1);

        try {
            String res = new JSONObject(s).getString("result");
            Integer score = Integer.valueOf(new JSONObject(res).getString("score"));
            System.out.println(score);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/faceCheck")
    @ResponseBody
    public UserInf matchImage(
            @RequestBody HashMap<String,String>map,
            HttpServletRequest req
    ) throws IOException {
        String base64Data = map.get("base64Data");
        Integer id = Integer.parseInt(map.get("id"));
        String path =  map.get("path");
        //获取待验证的图片base64码
        String encode = base64Data.split("base64,")[1];
        String realPath = req.getServletContext().getRealPath("/uploadFace") + "/" + path;

        //获取进行验证图片的base64码
        byte[] bytes = FileUtil.readFileByBytes(realPath);
        String encode1 = Base64Util.encode(bytes);
        String s = faceMatch(encode, encode1);

        try {
            String res = new JSONObject(s).getString("result");
            Double score = Double.valueOf(new JSONObject(res).getString("score"));
            System.out.println("score = :" + score);
            System.out.println(score > 90);
            if(score > 80){
                //验证成功!!
                System.out.println(userInfService.selectById(id));
                return userInfService.selectById(id);
            }else{
                //验证错误
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}