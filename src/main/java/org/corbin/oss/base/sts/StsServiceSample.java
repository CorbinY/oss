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

//        RoleArn表示的是需要扮演的角色ID，角色的ID可以在角色管理 > 角色详情中找到。
//        RoleSessionName是一个用来标示临时凭证的名称，一般来说建议使用不同的应用程序用户来区分。
//        Policy表示的是在扮演角色的时候额外加上的一个权限限制,取本地policyyu 角色权限的交集
//        DurationSeconds指的是临时凭证的有效期，单位是s，最小为900，最大为3600。
//        id和secret表示的是需要扮演角色的子账号的AccessKey

        String endpoint = "sts.aliyuncs.com";
        String accessKeyId = "LTAIdYyrM1mS0AuW";
        String accessKeySecret = "WB5nZSrwgvTuE35WimgEWzFXFVTvI2";
        String roleArn =
                "acs:ram::1600162136745558:role/user-oss-role";
        String roleSessionName = "session-nswame";
        String policy = "{\n" +
                "  \"Version\": \"1\",\n" +
                "  \"Statement\": [\n" +
                "    {\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Action\": [\n" +
                "        \"oss:GetObject\",\n" +
                "        \"oss:PutObject\",\n" +
                "        \"oss:ListObjects\"\n" +
                "      ],\n" +
                "      \"Resource\": [\n" +
                "        \"acs:oss:*:*:my-first-bk2/wechat/xiaowei/*\"\n" +
                "      ],\n" +
                "      \"Condition\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Action\": [\n" +
                "        \"oss:ListObjects\"\n" +
                "      ],\n" +
                "      \"Resource\": [\n" +
                "        \"acs:oss:*:*:my-first-bk2\"\n" +
                "      ],\n" +
                "      \"Condition\": {\n" +
                "        \"StringLike\": {\n" +
                "          \"oss:Prefix\": [\n" +
                "            \"\",\n" +
                "            \"wechat/\",\n" +
                "            \"wechat/xiaowei/*\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"StringEquals\": {\n" +
                "          \"oss:Delimiter\": \"/\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        System.out.println(policy);
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
            request.setDurationSeconds(900L);
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
