package org.corbin.oss.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;

@Service
public class FileUploadService {
    static String endPoint = "oss-cn-hangzhou.aliyuncs.com";
    static String keyId = "LTAIpn6Ibg2sFwhV";
    static String keySecret = "XuJZLedVaeHUsmHZOtmHgSkM7J2FTf";
    static String token = "CAISmgR1q6Ft5B2yfSjIr4iELI7cvKV21pq+UELmtlU3Z80fgYjOrDz2IHxEdXZgAuwctf8zmGtQ7v4TlqdoRoReREvCKM1r8Z1S/Fsu5CAnEYnng4YfgbiJREISYXyShb0jCoeUZdfZfejXRDKgvyRvwLz8WCy/Vli+S/OggoJmadJlGWvdaiFdVvhbOixoqsIRKRm0Mu22YCb3nk3aDkdjpmgbjnhku4C+2dG74BjTh0HhsMB3wb78OZ+5dcJhEY5iWtOupoxMe7HGzTRb5moJluZ/h7cW/izc7JPPQQtU8lCIMvDP7phlL1U+eaUxG6dY6+j9nPpnt+2U1fZYonQgFOVRVDulBeLE6cDYGeSvG88kfLLgQRrk2cyOMZ+HymFDZmkAZidLYMYGIH19AgA3MG+4TJWq41fXeAyuZrGY2aUtq/pPwk7v4MCBKir9IcWQ2j1KPYQnPQF6dUwTzCnvc7Udb0lGcAkdTdTxdplpbRZRhLr1tAnJLEhn1WoFk/D6ZvbXpq0FG+6dN9U8juh/X4hcsm0la1X0RsjMjVwPJlR/TLhXwNuYW/fdh6CIy+Wafamkcu8KsVVTbXfBriOKVn5Ram/TnoR9MQLBpZ6Il/KTr849TVsUi4oBXV3fQ7FEtEd75qa+1ROX/dL4X2qilmhCoYWIrtYfshc+Jaz437TK5maPphmNeqQj3cDMQyh1RhC6a8LdUSUWBPFtGoABOFrD84kFAcPBVkLqzdcaQz1yR8NAmQRojTAKwnL9hK+oht2l/CHr78ibgsbBcAcDcLhnPXpGQTdM/WdcQ6HeYiCjzpmm20n8GmsVengqh9NpCeCcqCGZzHjnXFxHR0c19PoKNPb5fcPMfMuEz9JYZkJbHuA3x3pW7UdXrKFJhLg=";

    static String bucket = "my-first-bk2";
    static String key = "wechat/xiaowei/";


    public void upload(){
     File file = new File("D:/123.docx");
        OSSClient oss = (OSSClient) new OSSClientBuilder().build(endPoint, keyId, keySecret);
        oss.putObject(bucket, key+"123.txt", new ByteArrayInputStream("123".getBytes()));
    }
}
