package com.xnx3.aliyun.chatbot.bean;


public class Recommend {
	private String knowledgeId;
	private String answerSource;
	private String title;
	
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
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
	@Override
	public String toString() {
		return "Recommend [knowledgeId=" + knowledgeId + ", answerSource=" + answerSource + ", title=" + title + "]";
	}
	
}
