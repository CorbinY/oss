package org.corbin.oss.controller;
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

import com.alibaba.fastjson.JSON;
import org.corbin.oss.base.oss.AliossConfigProperties;
import org.corbin.oss.service.AliossFileService;
import lombok.Getter;
import lombok.Setter;
import org.corbin.oss.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yin
 * @date 2019/07/04
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AliossFileController {
    private AliossFileService aliossFileService;
    private AliossConfigProperties aliossConfigProperties;
    private FileUploadService fileUploadService;

    @Autowired
    private AliossFileController(AliossFileService aliossFileService, AliossConfigProperties aliossConfigProperties, FileUploadService fileUploadService) {
        this.aliossFileService = aliossFileService;
        this.aliossConfigProperties = aliossConfigProperties;
        this.fileUploadService = fileUploadService;
    }

    class Wlan {
        @Getter
        @Setter
        Integer code;
        @Getter
        @Setter
        Object result;

    }


    /**
     * 获取对象上传权限,
     * 适用对象可以用xml请求
     *
     * @return
     */
    @GetMapping("/upload/sign")
    public Object testUploadFile() {
        Map<String, String> map = aliossFileService.fileUploadInit("my-first-bk2", "test/", 1000 * 1000 * 60L);
        System.out.println(aliossConfigProperties.getCallbackDomain());


        Wlan wlan = new Wlan();
        wlan.setCode(1);
        wlan.setResult(map);

        return wlan;
    }

    /**
     * 回调必须使用json，必须为键值对json
     * 至少对于官方给出的web demo
     *
     * @param filename
     * @param size
     * @return
     */
    @PostMapping("/upload/callback")
    public String fileUploadCallback(String filename, String size) {
        Wlan w = new Wlan();
        if (filename == null || size == null) {

            w.setCode(1);
            return JSON.toJSONString(w);
        } else {
            w.setCode(2);
        }
        return JSON.toJSONString(w);
    }


    @GetMapping("/test1")
    public String test1() {
        fileUploadService.upload();
        return "true";
    }
}
