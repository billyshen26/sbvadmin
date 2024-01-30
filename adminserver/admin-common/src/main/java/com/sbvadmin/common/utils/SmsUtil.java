package com.sbvadmin.common.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/4/23 22:25
 */
@Component
@Slf4j
public class SmsUtil {
    private static String accessKeyId;
    private static String accessKeySecret;

    // TIPS: @Value给静态变量注入值 // https://www.jb51.net/article/255800.htm
    @Value("${spring.sms.access-key-id}")
    public void setAccessKeyId(String key) {
        accessKeyId = key;
    }

    @Value("${spring.sms.access-key-secret}")
    public void setAccessKeySecret(String secret) {
        accessKeySecret = secret;
    }
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /**
     * Notes:  发送短信
     * @param: [phone, name, template, params]
     * @return: void
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/4/24 17:43
     **/
    public static void sendSMS(String phone,String name, String template,String params) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SmsUtil.createClient(accessKeyId, accessKeySecret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(name)
                .setTemplateCode(template)
                .setTemplateParam(params); //"{\"time\":\"3333\",\"title\":\"4444\"}"
        try {
            // 复制代码运行请自行打印 API 的返回值
           SendSmsResponse sendSmsResponse =  client.sendSmsWithOptions(sendSmsRequest, new com.aliyun.teautil.models.RuntimeOptions());
           if(sendSmsResponse.getBody().getCode().equals("OK")){
               log.info("短信发送成功================" + sendSmsResponse.getBody().getMessage());
           }else{
               log.error("短信code================" + sendSmsResponse.getBody().getCode());
               log.error("短信内容================" + sendSmsResponse.getBody().getMessage());
           }
        } catch (TeaException error) {
            log.error(error.message);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            log.error(error.message);
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}
