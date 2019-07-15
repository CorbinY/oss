package org.corbin.oss.base.sts;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public class StsServiceSample {
    public static void main(String[] args) {
        String endpoint = "sts.aliyuncs.com";
        String accessKeyId = "LTAIdYyrM1mS0AuW";
        String accessKeySecret = "WB5nZSrwgvTuE35WimgEWzFXFVTvI2";
        String roleArn =
                "acs:ram::1600162136745558:role/user-oss-role";
        String roleSessionName = "session-nswame";
        String policy = "{\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": \"oss:*\",\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Resource\": \"*\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Version\": \"1\"\n" +
                "}";
        try {
            // 构造 default profile（参数留空，无需添加 region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用 profile 构造 client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();

            request.setSysEndpoint(endpoint);
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // Optional
            final AssumeRoleResponse response = client.getAcsResponse(request);
            System.out.println("Expiration: " + response.getCredentials().getExpiration());
            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
            System.out.println("RequestId: " + response.getRequestId());
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
    }
}
