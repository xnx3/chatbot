package com.xnx3.aliyun.chatbot.bean;
 
public class Text {
	private String Content;
	private String answerSource;
	
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getAnswerSource() {
		return answerSource;
	}
	public void setAnswerSource(String answerSource) {
		this.answerSource = answerSource;
	}
	
	@Override
	public String toString() {
		return "Text [Content=" + Content + ", answerSource=" + answerSource + "]";
	}
	

	
}
