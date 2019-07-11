package org.corbin.oss.base.oss;
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

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yin
 * @date 2019/07/04
 */
@Configuration
@ConditionalOnClass(OSSClient.class)
//@EnableConfigurationProperties(AliossConfigProperties.class)注解无效
public class AliossConfig {

    private AliossConfigProperties ossProperties;

    @Autowired
    public AliossConfig(AliossConfigProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     * 创建OSSCLient bean
     * @return
     */
    @Bean
    public AliossClientFactoryBean aliossClientFactoryBean() {
        final AliossClientFactoryBean factoryBean = AliossClientFactoryBean
                .builder()
                .endpoint(this.ossProperties.getEndpoint())
                .accessKeyId(this.ossProperties.getAccessKeyId())
                .maxErrorRetry(this.ossProperties.getMaxErrorRetry())
                .accessKeySecret(this.ossProperties.getAccessKeySecret())
                .connectionTimeout(this.ossProperties.getConnectionTimeout())
                .build();
        return factoryBean;
    }

}
