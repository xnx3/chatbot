package com.xnx3.aliyun.chatbot.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * chatbot 的回答，将接收的json格式的内容字符串转化为 answer 对象
 * @author 管雷鸣
 *
 */
public class Answer {
	
	/**
	 * 当用户问问题后，没有匹配到答案，但是有相似的几个问题提示，列出着相似的几个问题的标题。这里是标题列表之前的提示文字。 
	 */
	public static String recomment_head_str = "猜您是不是想问：";
	
	private String SessionId;
	private String MessageId;
	private String RequestId;
	private Message message;
	private String text;	//回答内容。不管是关联的，还是闲聊等
	 
	public Answer(String responseContent) {
		JSONObject obj = JSONObject.fromObject(responseContent);
		if(obj.get("SessionId") == null){
			return;
		}
		
		this.SessionId = obj.getString("SessionId");
		this.MessageId = obj.getString("MessageId");
		this.RequestId = obj.getString("RequestId");
		
		if(obj.get("Messages") != null){
			JSONArray messageArray = obj.getJSONArray("Messages");
			if(messageArray.size() > 0){
				JSONObject mo = messageArray.getJSONObject(0);
				
				//setMessage(mo);
				//if(message == null){
					message = new Message(mo);
					
					switch (message.getType()) {
					case Message.TYPE_KNOWLEDGE:
						//在知识库中匹配到具体问题了，那么直接返回。优先级最高
						if(message.getKnowledge() != null){
							this.text = message.getKnowledge().getContent();
							break;
						}
						//不讲break加到这里，是为了如果 message.getKnowledge() 为 null时，继续向下走，别因为text未赋予值而返回个null
					case Message.TYPE_TEXT:
						//直接返回文本，那么直接返回就好。优先级最高
						if(message.getText() != null && message.getText().getContent() != null){
							this.text = message.getText().getContent();
							break;
						}
					case Message.TYPE_RECOMMEND:
						//最后是找相似问题
						if(message.getRecommendList().size() > 0){
							//有相似的，那么将相似的列出来
							
							StringBuffer sb = new StringBuffer();
							sb.append(recomment_head_str+"<br/><ul class=\"xnx3_chatbot\">");
							for (int i = 0; i < message.getRecommendList().size(); i++) {
								Recommend recommend = message.getRecommendList().get(i);
								sb.append("<li class=\"xnx3_recommend_li\">"+recommend.getTitle()+"</li>");
							}
							sb.append("</ul>");
							this.text = sb.toString();
							break;
						}
					default:
						this.text = "抱歉，我没有听懂你说的什么";
					}
					
				//}
				
			}
		}
		
	}
	
	
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	public String getRequestId() {
		return RequestId;
	}
	public void setRequestId(String requestId) {
		RequestId = requestId;
	}
	public Message getMessage() {
		if(message == null){
			message = new Message();
		}
		if(message.getText() == null){
			message.setText(new Text());
		}
		if(message.getText().getContent() == null){
			message.getText().setContent("");
		}
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	public String getText() {
		if(text == null){
			return "";
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Answer [SessionId=" + SessionId + ", MessageId=" + MessageId + ", RequestId=" + RequestId + ", message="
				+ message + ", text=" + text + "]";
	}
	
	
}
