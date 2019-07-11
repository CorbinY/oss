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

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author yin
 * @date 2019/07/04
 * 使用factory创建的bean，请优先使用@Autowise注入
 * 使用构造方法注入时，other object = this bean 时，other object=null=this object！！！！
 * 但是，在方法中this beaan 调用其他方法可正常使用
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AliossClientFactoryBean implements FactoryBean<OSSClient>, InitializingBean, DisposableBean {
    private OSSClient ossClient;

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private Integer connectionTimeout;
    private Integer maxErrorRetry;


    @Override
    public OSSClient getObject() {
        return this.ossClient;
    }

    @Override
    public Class<?> getObjectType() {
        return OSSClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() {
        if (this.ossClient != null) {
            this.ossClient.shutdown();
        }
    }

    @Override
    public void afterPropertiesSet() {

        Assert.notNull(this.accessKeyId, "'accessKeyId' must be not null");
        Assert.notNull(this.accessKeySecret, "'accessKeySecret' must be not null");
        Assert.notNull(this.endpoint, "'endpoint' must be not null");
        Assert.notNull(this.maxErrorRetry, "'maxErrorRetry' must be not null");
        Assert.notNull(this.connectionTimeout, "'connectionTimeout' must be not null");

        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setConnectionTimeout(this.connectionTimeout);
        configuration.setMaxErrorRetry(this.maxErrorRetry);

        this.ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret, configuration);
    }

}
