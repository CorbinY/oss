//package com.quanwei.ossbigflie.base.service;
//
//import com.aliyun.oss.OSSClient;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import javax.validation.constraints.NotNull;
//
///**
// * @author yin
// * @date 2019/07/04
// */
//@Service
//@NoArgsConstructor
//public class OssBaseService {
//    @Autowired
//    protected OSSClient ossClient;
//
//
//    /**
//     * 初始化ossclient，当ossclient为null时，
//     * 采用默认配置文件中配置的ossclient
//     *
//     * @param ossClient
//     * @return
//     */
//    protected OSSClient ossClientInit(OSSClient ossClient) {
//        return ossClient == null ? this.ossClient : ossClient;
//    }
//
//    protected OSSClient createOssClient(@NotNull String endpoint, @NotNull String acessKeyId, @NotNull String accessKeySecret) {
//        Assert.notNull(endpoint, "endpoint must not be null");
//        Assert.notNull(acessKeyId, "acessKeyId must not be null");
//        Assert.notNull(accessKeySecret, "accessKeySecret must not be null");
//
//        return new OSSClient(endpoint, acessKeyId, accessKeySecret);
//    }
//
//}
