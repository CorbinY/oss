package org.corbin.oss.base.sts;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSBuilder;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;

public class FileUpload {
    static String endPoint = "oss-cn-hangzhou.aliyuncs.com";
    static String keyId = "STS.NHji8yBXCLCioVgmbX1sVQYQG";
    static String keySecret = "7BHzey1W4QgH6MHe1b5NN8yHguiQ9kMp74sGru9qbcub";
    static String token = "CAISmgR1q6Ft5B2yfSjIr4vfIoLNr4di+4GCbXDWiWINPfx6vpz6pTz2IHxEdXZgAuwctf8zmGtQ7v4TlqdoRoReREvCKM1r8Z1S/Fs78VEmEYnng4YfgbiJREISYXyShb0jCoeUZdfZfejXRDKgvyRvwLz8WCy/Vli+S/OggoJmadJlGWvdaiFdVvhbOixoqsIRKRm0Mu22YCb3nk3aDkdjpmgbjnhku4C+2dG74BjTh0HhsMB3wb78OZ+5dcJhEY5iWtOupoxMe7HGzTRb5moJluZ/h7cW/izc7JPPQQtU8lCIMvDP7phlL1U+eaUxG6dY6+j9nPpnt+2U1fZYonQgFOVRVDulBeLE6cDYGeSvG88kfLLgQRrk2cyOMZ+HymFDZmkAZidLYMYGIH19AgA3MG+4TJWq41fXeAyuZrGY2aUtq/pPwk7v4MCBKir9IcWQ2j1KPYQnPQF6dUwTzCnvc7Udb0lGcAkdTdTxdplpbRZRhLr1tAnJLEhn1WoFk/D6ZvbXpq0FG+6dN9U8juh/X4hcsm0la1X0RsjMjVwPJlR/TLhXwNuYW/fdh6CIy+Wafamkcu8KsVVTbXfBriOKVn5Ram/TnoR9MQLBpZ6Il/KTr849TVsUi4oBXV3fQ7FEtEd75qa+1ROX/dL4X2qilmhCoYWIrtYfshc+Jaz437TK5maPphmNeqQj3cDMQyh1RhC6a8LdUSUWBPFtGoABFYF/WDsGdNDUJd50SXW3uozE5zrYu1cG+IWl3mrOQa/gejMWrYbHhCCfYDxKNBv2Dwy0mSM4288o9c2BSKVJ/liflN5949OzPihrJtQrVq8czQC9ylT/OfLiW2gq7bwMkhXp0mK2JIDyz1B6nWx3GyZl+WbGnY9j5mnEO1hPx+0=";

    static String bucket = "my-first-bk2";
    static String key = "wechat/";

    public static void main(String[] args) {
        File file = new File("D:/123.docx");
        OSSClient oss = (OSSClient) new OSSClientBuilder().build(endPoint, keyId, keySecret,token);
        oss.putObject(bucket, key+"123.txt", file);
        oss.shutdown();
    }

}
