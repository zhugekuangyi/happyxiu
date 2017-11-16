package com.mingsheng.utils;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.aliyun.oss.ServiceException;

/**
 * 阿里大于短信发送工具类
 */
public class SmsUtils {

	private static String appKey = "LTAINKJKG7yIiS6C";
	private static String appSecret ="IiM0oZab7rdT1wVvqvJuiOsINLwI6I";
	
	public static void veriCode(String phone,String code){
		/**
         * Step 1. 获取主题引用
         */                                                         
        CloudAccount account = new CloudAccount(appKey, appSecret, "https://1615476250595714.mns.cn-hangzhou.aliyuncs.com/");
        MNSClient client = account.getMNSClient();
        System.out.println(client.isOpen());
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("快乐修");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("快乐修");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_26450033");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", code);
        smsReceiverParams.setParam("product", "快乐修");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
        //batchSmsAttributes.addSmsReceiver("", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
	}
    public static void veriCodeNew(String phone,String code,String type){
        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(appKey, appSecret, "https://1615476250595714.mns.cn-hangzhou.aliyuncs.com/");
        MNSClient client = account.getMNSClient();
        System.out.println(client.isOpen());
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("快乐修");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("快乐修");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        if("1".equals(type)){
            //注册验证码
            batchSmsAttributes.setTemplateCode("SMS_26450033");
        }else if("2".equals(type)){
            //登录验证码
            batchSmsAttributes.setTemplateCode("SMS_26450033");
        }else if("3".equals(type)){
            //修改密码验证码
            batchSmsAttributes.setTemplateCode("SMS_26450033");
        }
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", code);
        smsReceiverParams.setParam("product", "快乐修");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
        //batchSmsAttributes.addSmsReceiver("18956858720", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
	
	public static void main(String[] args) {
		veriCodeNew("1395712840", "1234","1");

//		veriCode("13957128430","1234");
	}

}
