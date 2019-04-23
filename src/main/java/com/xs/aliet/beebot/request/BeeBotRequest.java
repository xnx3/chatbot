package com.xs.aliet.beebot.request;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 云小蜜请求对象
 * @author 小帅丶
 *
 */
public class BeeBotRequest extends GeneralRequest{

	/**
	 * @param format  返回值的类型，支持 JSON 与 XML。默认为 XML。
	 * @param version API 版本号，本版本对应为2017-10-11。
	 * @param accessKeyId 阿里云颁发给用户的访问服务所用的密钥 ID。
	 * @param signature 签名结果串
	 * @param signatureMethod 签名方式，目前支持 HMAC-SHA1。
	 * @param timestamp 请求的时间戳。日期格式按照 ISO8601 标准表示，并需要使用UTC时间。格式为：YYYY-MM-DDThh:mm:ssZ
	 * @param signatureVersion 签名算法版本，目前版本是 1.0。
	 * @param signatureNonce 唯一随机数
	 * @param action  系统规定参数，取值：Chat
	 * @param instanceId 机器人实例ID
	 * @param utterance 机器人访问者的输入
	 */
	public BeeBotRequest(String format, String version, String accessKeyId,
			String signature, String signatureMethod, String timestamp,
			String signatureVersion, String signatureNonce, String action,
			String instanceId, String utterance) {
		super(format, version, accessKeyId, signature, signatureMethod,
				timestamp, signatureVersion, signatureNonce);
		Action = action;
		InstanceId = instanceId;
		Utterance = utterance;
	}
	/**
	 * @param accessKeyId 阿里云颁发给用户的访问服务所用的密钥 ID。
	 * @param timestamp 请求的时间戳。日期格式按照 ISO8601 标准表示，并需要使用UTC时间。格式为：YYYY-MM-DDThh:mm:ssZ
	 * @param signatureNonce 唯一随机数
	 * @param action  系统规定参数，取值：Chat
	 * @param instanceId 机器人实例ID
	 * @param utterance 机器人访问者的输入
	 */
	public BeeBotRequest(String accessKeyId,
			String timestamp,
			String signatureNonce, String action,
			String instanceId, String utterance) {
		super(accessKeyId,timestamp,signatureNonce);
		Action = action;
		InstanceId = instanceId;
		Utterance = utterance;
	}

	@JSONField(name="Action")
	private String Action;
	@JSONField(name="InstanceId")
	private String InstanceId;
	@JSONField(name="Utterance")
	private String Utterance;
	@JSONField(name="SessionId")
	private String SessionId;
	@JSONField(name="KnowledgeId")
	private String KnowledgeId;
	@JSONField(name="SenderId")
	private String SenderId;
	@JSONField(name="SenderNick")
	private String SenderNick;
	@JSONField(name="Tag")
	private String Tag;
	//private String Perspective.1;
	
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getInstanceId() {
		return InstanceId;
	}
	public void setInstanceId(String instanceId) {
		InstanceId = instanceId;
	}
	public String getUtterance() {
		return Utterance;
	}
	public void setUtterance(String utterance) {
		Utterance = utterance;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getKnowledgeId() {
		return KnowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		KnowledgeId = knowledgeId;
	}
	public String getSenderId() {
		return SenderId;
	}
	public void setSenderId(String senderId) {
		SenderId = senderId;
	}
	public String getSenderNick() {
		return SenderNick;
	}
	public void setSenderNick(String senderNick) {
		SenderNick = senderNick;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
}
