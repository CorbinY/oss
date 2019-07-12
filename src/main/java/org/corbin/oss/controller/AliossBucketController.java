package org.corbin.oss.controller;

import com.aliyun.oss.OSS;
import lombok.extern.slf4j.Slf4j;
import org.corbin.oss.base.AliossSupport;
import org.corbin.oss.service.AliossBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bucket")
public class AliossBucketController {
    private AliossBucketService aliossBucketService;

    @Autowired
    private AliossBucketController(AliossBucketService aliossBucketService) {
        this.aliossBucketService = aliossBucketService;
    }


    @GetMapping("/create")
    public String createBucketTest(String bucketName) {
        aliossBucketService.crteateBucket(bucketName);
        return "sdy";
    }
    @GetMapping("/check")
    public String isBucketExist(String bucketName) {
        aliossBucketService.isBucketExit(bucketName);
        return "sdy";
    }
}
