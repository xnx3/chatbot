# chatbot
智能客服机器人，录入问题库，输入问题，返回答案。一行代码，就是这么简单。

使用的阿里云小蜜，需要自行开通阿里云小蜜功能，将其相应参数填入即可使用。


java 8 所写

## 微信扫码体验
![微信扫码体验](https://images.gitee.com/uploads/images/2019/0328/140434_bff68d69_429922.png "qrcode.png")
<br/>
体验页面提供者：[佛山市索凡科技有限公司](http://www.suofan.net.cn/)

## 功能
1. 自定义知识库。云小蜜后台可自由录入你自己的知识库，问题及答案。
1. 自带常用聊天
1. 返回的问题答案为html格式，直接显示出来，无需任何处理。


## 示例
问：你好<br/>
答：哈喽，人见人爱的我来啦，有啥我可以帮您的吗~<br/>
<br/>
问：你是谁<br/>
答：hi，我是您的小天使呢，为您答疑解惑，帮您排忧解难呢<br/>
<br/>
问：发票<br/>
答：猜您是不是想问：<br/><ul class="xnx3_chatbot"><li class="xnx3_recommend_li">非结算型业务支付对象如何知道要开具发票？</li><li class="xnx3_recommend_li">发票抬头写错了怎么办？</li><li class="xnx3_recommend_li">需要调取已经付款的发票怎么办？</li></ul><br/>



## 快速使用说明
#### 1. maven项目的 pom.xml 中加入
````
<dependency>
	<groupId>com.xnx3.chatbot</groupId>
	<artifactId>chatbot</artifactId>
	<version>1.0</version>
</dependency>
```` 

#### 2. 阿里云开通云小蜜服务
https://chatbot.console.aliyun.com

#### 3. 代码中使用示例 
````
import java.io.IOException;
import com.xnx3.aliyun.chatbot.ChatbotUtil;

public class Test {
	static ChatbotUtil chat;
	static{
		/* 
		 * 参数说明
		 * accessKeyId 阿里云的 Access Key Id
		 * accessKeySecret 阿里云的 Access Key Secret
		 * chatbotUrl 云小蜜接口地址，传入如： https://chatbot.cn-shanghai.aliyuncs.com/   如果不懂，固定传入这个url即可
		 * chatbotInstanceId 云小蜜的机器人ID，如： chatbot-cn-1234567890 。机器人实例ID。登录云小蜜控制台，左侧面板选择开发者->基本配置，查看机器人示例信息，可获得该实例ID。
		 */
		chat = new ChatbotUtil("LTA1234567890", "dTuD12345678901234567890", "https://chatbot.cn-shanghai.aliyuncs.com/", "chatbot-cn-1234567890");
	}
	public static void main(String[] args) throws IOException {
		
		//chat创建后，可多次使用
		System.out.println(chat.question("你好").getText());
		System.out.println(chat.question("发票").getText());
		
	}
}
````

#### 4. 前端html界面
参见 https://gitee.com/leimingyun/aichat