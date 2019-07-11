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
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.quanwei.ossbigflie.base.oss.AliossSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author yin
 * @date 2019/07/04
 */
@Slf4j
@Service
public class AliossBucketService {
//    private final String bucketNameRegex="[a-z]";

    private AliossSupport aliossSupport;

    @Autowired
    private AliossBucketService(AliossSupport aliossSupport) {
        this.aliossSupport = aliossSupport;
    }

    /**
     * 创建bucket
     *
     * @param bucketName
     */
    public Bucket crteateBucket(String bucketName) {
        Assert.notNull(bucketName, "bucketName must not be null");
        OSSClient ossClient = aliossSupport.defaultOssClient();

        //判断bucket 是否存在，不能创建重名bucket，否则会抛异常
        boolean doesExit = isBucketExit(bucketName);
        return doesExit ? null : ossClient.createBucket(bucketName);

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
        OSSClient ossClient = aliossSupport.defaultOssClient();
        return ossClient.doesBucketExist(bucketName);
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
