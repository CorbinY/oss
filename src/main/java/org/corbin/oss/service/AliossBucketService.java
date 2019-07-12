package org.corbin.oss.service;
/*
 * Copyright  2019  yinyanbin
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
 */

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.corbin.oss.base.AliossSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author yin
 * @date 2019/07/04
 */
@Slf4j
@Service
public class AliossBucketService {
    /**
     * 创建bucket,同时设置加密策略，默认AES256
     *
     * @param bucketName
     * @return
     */
    public Bucket crteateBucket(String bucketName) {

        Assert.notNull(bucketName, "bucketName must not be null");
        OSSClient ossClient = AliossSupport.defaultOssClient();
        //判断bucket 是否存在，不能创建重名bucket，否则会抛异常
        boolean doesExit = isBucketExit(bucketName);
        Bucket bucket = doesExit ? null : ossClient.createBucket(bucketName);

        //设置bucket加密
        encryptionBucket(bucketName);
        return bucket;
    }

    /**
     * 获取bucket的加密方式
     *
     * @param bucketName
     * @return
     */
    public ServerSideEncryptionConfiguration getBucketEncryption(String bucketName) {
        OSS oss = AliossSupport.defaultOssClient();
        // 获取Bucket加密配置
        return oss.getBucketEncryption(bucketName);
    }

    /**
     * 删除bucket的加密
     *
     * @param bucketname
     */
    public void deleteBucketEncryption(String bucketname) {
        OSS oss = AliossSupport.defaultOssClient();
        oss.deleteBucketEncryption(bucketname);
    }

    /**
     * 设置bucket加密,默认使用AES256
     * 上传文件时，OSS服务端使用完全托管的AES256进行加密操作。
     * OSS会为每个对象使用不同的密钥进行加密，作为额外的保护，
     * 它将使用定期轮转的主密钥对加密密钥本身进行加密。
     */
    public void encryptionBucket(String bucketName) {
        Assert.notNull(bucketName, "bucketName must not be null");
        Assert.isTrue(isBucketExit(bucketName), "bucket is not exit");

        OSS oss = AliossSupport.defaultOssClient();

        //默认机密策略
        ServerSideEncryptionByDefault serverSideEncryptionByDefault = new ServerSideEncryptionByDefault(SSEAlgorithm.AES256);

        //服务端加密策略
        ServerSideEncryptionConfiguration sseConfig = new ServerSideEncryptionConfiguration();
        sseConfig.setApplyServerSideEncryptionByDefault(serverSideEncryptionByDefault);
        //加密
        SetBucketEncryptionRequest request = new SetBucketEncryptionRequest(bucketName, sseConfig);
        oss.setBucketEncryption(request);

    }


    /**
     * 判断bucket 是否已存在或崇明
     * bucketName 全局唯一，作为三级域名使用
     *
     * @param bucketName
     * @return
     */
    public boolean isBucketExit(String bucketName) {
        Assert.notNull(bucketName, "bucketName must not be null");
        OSSClient ossClient = AliossSupport.defaultOssClient();
        boolean doesExist = ossClient.doesBucketExist(bucketName);
        log.info("查询bucketName:{}是存在性{}，存在为true，不存在为false", bucketName, doesExist);
        return doesExist;

    }

    /**
     * 检查bucket是否存在，
     * 如果不存在，则创建bucket
     *
     * @param bucketName
     */
    public void updateBucketExitStatus(String bucketName) {
        Assert.notNull(bucketName, "bucketName must not be null");

        if (!isBucketExit(bucketName)) {
            crteateBucket(bucketName);
        }
    }


}
