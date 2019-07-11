package com.quanwei.ossbigflie.base.oss;
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
 * @author cn-src
 */

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yin
 * <p>
 * oss 基础配置属性
 */
@Getter
@ToString
@Configuration
//  - 表示下一级的连接符
@ConfigurationProperties(prefix = "alibaba.cloud.oss-default")
@PropertySource(value = "classpath:oss-config.yml", ignoreResourceNotFound = true, encoding = "UTF-8")
public class AliossConfigProperties  {


    /**
     * oss 默认域名，初始化oss连接必要参数
     */
    @Value("${endpoint}")
    private String endpoint;

    /**
     * oss默认账户名，初始化oss连接必要参数
     */
    @Value("${accessKeyId}")
    private String accessKeyId;

    /**
     * oss默认账户秘钥，初始化oss连接必要参数
     */
    @Value("${accessKeySecret}")
    private String accessKeySecret;

    /**
     * oss 默认存储空间名，非必要初始化oss连接必要参数
     */
    @Value("${bucketName}")
    private String bucketName;

    /**
     *  oss默认回调域名地址，非必要初始化oss连接必要参数
     */
    @Value("${callbackDomain}")
    private String callbackDomain;



}
