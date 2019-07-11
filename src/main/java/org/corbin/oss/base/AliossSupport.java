package org.corbin.oss.base;
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

import com.aliyun.oss.OSSClient;
import org.corbin.oss.base.SpringUtils;
import org.corbin.oss.base.oss.AliossConfigProperties;
import org.springframework.util.Assert;

/**
 * oss 的封装类,
 *
 * @author yin
 */
public class AliossSupport {

    /**
     * 返回默认ossClient，即使用配置文件初始化的ossclient
     * @return
     */
    public static OSSClient defaultOssClient() {
        OSSClient ossClient = SpringUtils.getBean("aliossClientFactoryBean");
        Assert.notNull(ossClient, "未获取到OSSClient bean");
        return ossClient;
    }

    /**
     * 获取AliossConfigProperties bean
     *
     * @return
     */
    public static AliossConfigProperties getAliossConfigProperties() {
        AliossConfigProperties aliossConfigProperties = SpringUtils.getBean("aliossConfigProperties");
        Assert.notNull(aliossConfigProperties, "AliossConfigProperties bean 获取失败");
        return aliossConfigProperties;
    }


    /**
     * 返回默认地域访问名，即使用配置文件初始化
     *
     * @return
     */
    public static String defaultEndpoint() {
        return getAliossConfigProperties().getEndpoint();
    }

    /**
     * 返回默认AccessKeyId，，即使用配置文件初始化
     *
     * @return
     */
    public static String defaultAccessKeyId() {
        return getAliossConfigProperties().getAccessKeyId();
    }

    /**
     * 返回默认访问秘钥，即使用配置文件初始化
     *
     * @return
     */
    public static String defaultAccessKeySecret() {
        return getAliossConfigProperties().getAccessKeySecret();
    }

    /**
     * 返回默认的bucket，即使用配置文件初始化
     *
     * @return
     */
    public static String defaultBucketName() {
        return getAliossConfigProperties().getBucketName();
    }

    /**
     * 返回默认回调域名，即使用配置文件初始化
     *
     * @return
     */
    public static String defaultCallbackDomain() {
        return getAliossConfigProperties().getCallbackDomain();
    }

    //--------------------------------------------------------------------------------------------------------------
    /**
     * 日后有需要自定义ossclient
     */

//    /**
//     * 自定义OSSClient，使用后需要
//     * @param endpoint
//     * @param accessKeyId
//     * @param secretAccessKey
//     * @return
//     */
//    public OSSClient instanceOssClient(String endpoint, String accessKeyId, String secretAccessKey) {
//        return new OSSClient(endpo

}
