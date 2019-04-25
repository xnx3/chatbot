package com.xnx3.aliyun.chatbot.bean;

import net.sf.json.JSONObject;
 
/**
 * 知识库关联问题
 * @author 管雷鸣
 *
 */
public class Knowledge {
	private String id;
	private String content;
	private String answerSource;
	private String title;
	private String summary;
	
	public Knowledge() {
	}
	
	/**
	 * 传入 阿里云 返回的 json对象，创建 Knowledge 对象
	 * @param json 传入json对象
	 */
	public Knowledge(JSONObject json) {
		if(json == null){
			return;
		}
		if(json.get("Id") != null){
			id = json.getString("Id");
		}
		if(json.get("Content") != null){
			content = json.getString("Content");
		}
		if(json.get("AnswerSource") != null){
			answerSource = json.getString("AnswerSource");
		}
		if(json.get("Title") != null){
			title = json.getString("Title");
		}
		if(json.get("Summary") != null){
			summary = json.getString("Summary");
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerSource() {
		return answerSource;
	}
	public void setAnswerSource(String answerSource) {
		this.answerSource = answerSource;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
