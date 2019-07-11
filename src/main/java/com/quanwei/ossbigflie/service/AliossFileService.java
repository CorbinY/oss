package com.quanwei.ossbigflie.service;
/*
 * Copyright (c) 2018 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * @author yin
 */
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.quanwei.ossbigflie.base.oss.AliossSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yin
 * @date 2019/07/04
 */
@Slf4j
@Service
public class AliossFileService {
    // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
    //  private final static String FILE_UPLOAD_CALL_BACK_URL = OSSConnection.callbackDoman() + "/wlan_scope_file_service/file/wechat/upload/callback";


    private AliossSupport aliossSupport;
    private AliossBucketService aliossBucketService;

    @Autowired
    private AliossFileService(AliossBucketService aliossBucketService, AliossSupport aliossSupport) {
        this.aliossBucketService = aliossBucketService;
        this.aliossSupport = aliossSupport;
    }

    /**
     * 初始化文件上传
     *
     * @param bucketName 存储空间名字
     * @param validTime  有效时长 单位ms
     * @param prefixDir  前缀目录,需要添加‘/’
     * @return
     */
    public Map<String, String> fileUploadInit(String bucketName, String prefixDir, Long validTime) {
        OSSClient ossClient = aliossSupport.defaultOssClient();
        // host的格式为 bucketname.endpoint
        String host = "https://" + bucketName + "." + aliossSupport.defaultEndpoint();
        //    log.info("---------------------callBack is : "+FILE_UPLOAD_CALL_BACK_URL);

        //设置policy 签名
        long expireEndTime = System.currentTimeMillis() + validTime;
        Date expiration = new Date(expireEndTime);

        PolicyConditions policyConds = new PolicyConditions();
        //单位字节 5G
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 5L * 1024 * 1024 * 1024);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, prefixDir);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);

        //设置签名
        byte[] binaryData = new byte[0];
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", aliossSupport.defaultAccessKeyId());
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", prefixDir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        String FILE_UPLOAD_CALL_BACK_URL = aliossSupport.defaultCallbackDomain() + "/upload/callback";
        JSONObject jasonCallback = new JSONObject();
        jasonCallback.put("callbackUrl", FILE_UPLOAD_CALL_BACK_URL);
        jasonCallback.put("callbackBody",
                "filename=${object}&size=${size}");
        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
        respMap.put("callback", base64CallbackBody);

        // 由spring 托管无需关闭
        //ossClient.shutdown();

        log.info(FILE_UPLOAD_CALL_BACK_URL);
        return respMap;

    }


    public String testTempUoload(String bucketname, String fileName) {
        OSSClient ossClient = aliossSupport.defaultOssClient();
        Date date = new Date(System.currentTimeMillis() + 1000 * 200000);
        URL url = ossClient.generatePresignedUrl(bucketname, fileName, date, HttpMethod.GET);

        return url.toString();
    }


    public String getAuthorization(String accessKeyId, String signature) {
        return accessKeyId + ":" + signature;
    }

}
