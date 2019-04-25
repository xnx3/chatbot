package com.xnx3.aliyun.chatbot;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import com.xnx3.aliyun.chatbot.bean.Answer;
import com.xnx3.aliyun.chatbot.support.AliETSignUtil;
import com.xnx3.net.HttpUtil;
import com.xnx3.net.HttpsUtil;
import com.xs.aliet.beebot.request.BeeBotRequest;
import com.xs.aliet.util.AliYunHttpUtils;

/**
 * 智能客服-云小蜜
 * @author 管雷鸣
 *
 */
public class ChatbotUtil {
	public static String APIVersion = "2017-10-11";	//API 版本号，本版本对应为2017-10-11。固定即可
	
	private String accessKeyId; 		//阿里云的 Access Key Id
	private String accessKeySecret;		//阿里云的 Access Key Secret
	
	private String chatbotUrl;			//云小蜜接口地址，传入如： https://chatbot.cn-shanghai.aliyuncs.com/
	private String chatbotInstanceId;	//云小蜜的机器人ID，如： chatbot-cn-1234567890 。机器人实例ID。登录云小蜜控制台，左侧面板选择开发者->基本配置，查看机器人示例信息，可获得该实例ID。
	
	/**
	 * 创建 智能客服-云小蜜 实例。一次创建，多次使用。
	 * <p>使用示例：</p>
	 * <pre>
	 * ChatbotUtil chat = new ChatbotUtil("LTA1234567890", "dTuD12345678901234567890", "https://chatbot.cn-shanghai.aliyuncs.com/", "chatbot-cn-1234567890");
	 * System.out.println(chat.question("你好").getText());
	 * System.out.println(chat.question("发票").getText());
	 * </pre>
	 * @param accessKeyId 阿里云的 Access Key Id
	 * @param accessKeySecret 阿里云的 Access Key Secret
	 * @param chatbotUrl 云小蜜接口地址，传入如： https://chatbot.cn-shanghai.aliyuncs.com/   如果不懂，固定传入这个url即可
	 * @param chatbotInstanceId 云小蜜的机器人ID，如： chatbot-cn-1234567890 。机器人实例ID。登录云小蜜控制台，左侧面板选择开发者，找到基本配置，查看机器人示例信息，可获得该实例ID。
	 */
	public ChatbotUtil(String accessKeyId, String accessKeySecret, String chatbotUrl, String chatbotInstanceId) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		
		this.chatbotUrl = chatbotUrl;
		this.chatbotInstanceId = chatbotInstanceId;
	}
	
	public static void main(String[] args) throws IOException {
		ChatbotUtil chat = new ChatbotUtil("LTA1234567890", "dTuD12345678901234567890", "https://chatbot.cn-shanghai.aliyuncs.com/", "chatbot-cn-1234567890");
		System.out.println(chat.question("你好").getText());
		System.out.println(chat.question("发票").getText());
	}
	
	
	public Answer question(String questionContent) throws IOException{
		String timeStamp = AliETSignUtil.getSolrDate(new Date());
		String nonce_str = UUID.randomUUID().toString();
		
		//将 + 替换为空格+ 避免造成JSON异常
		questionContent = questionContent.replaceAll("\\+", "");
		questionContent = questionContent.replaceAll(" ", "");
		 
		
		BeeBotRequest bot = new BeeBotRequest("JSON",APIVersion,accessKeyId,""," HMAC-SHA1",timeStamp,"1.0",nonce_str,"Chat", chatbotInstanceId, questionContent);
		HashMap<String, Object> hashMap1 = AliETSignUtil.transBean2Map(bot);
		String sign1 = AliETSignUtil.getSignature("GET", hashMap1, accessKeySecret);
		hashMap1.put("Signature", sign1);
		String param1 = AliETSignUtil.getParams(hashMap1);
		String result1  = AliYunHttpUtils.sendGet(chatbotUrl, param1);
		
		
		Answer ans = new Answer(result1);
		return ans;
	}
	
}
