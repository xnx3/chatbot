package com.xnx3.aliyun.chatbot.bean;

import java.util.ArrayList;
import java.util.List;
import com.xnx3.json.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
/**
 * 消息
 * @author 管雷鸣
 *
 */
public class Message {
	
	/**
	 * 文本
	 */
	public final static String TYPE_TEXT = "Text";
	/**
	 * 知识库关联问题推荐
	 */
	public final static String TYPE_RECOMMEND = "Recommend";
	/**
	 * 知识库关联问题
	 */
	public final static String TYPE_KNOWLEDGE = "Knowledge";
	
	private Text text;
	private String type;	//本条消息的类型 Text=文本，Recommend=知识库关联问题推荐，Knowledge=知识库关联问题
	private Knowledge knowledge;
	private List<Recommend> recommendList;	//问题没有完全匹配，但是很相近，其相关的问、答会在这里
	
	public Message() {
	}
	public Message(JSONObject json) {
		if(json == null){
			return;
		}
		String type = json.getString("Type");
		setType(type);
		if(type.equals(Message.TYPE_TEXT)){
			//文本
			JSONObject textObj = json.getJSONObject("Text");
			Text text = new Text();
			if(textObj.get("Content") != null){
				text.setContent(JSONUtil.getString(textObj, "Content"));
			}
			if(textObj.get("AnswerSource") != null){
				text.setAnswerSource(JSONUtil.getString(textObj, "AnswerSource"));
			}
			
			setText(text);
//			this.text = text.getContent();
		}else if (type.equals(Message.TYPE_KNOWLEDGE)) {
			//知识库关联问题
			Knowledge knowledge = new Knowledge(json.getJSONObject("Knowledge"));
			setKnowledge(knowledge);
//			this.text = knowledge.getContent();
		}else if(type.equals(Message.TYPE_RECOMMEND)){
			//知识库关联问题推荐
			JSONArray rl = json.getJSONArray("Recommends");
			List<Recommend> recommendList = new ArrayList<Recommend>();
			for (int i = 0; i < rl.size(); i++) {
				JSONObject reJson = rl.getJSONObject(i);
				Recommend recommend = new Recommend();
				recommend.setTitle(JSONUtil.getString(reJson, "Title"));
				recommend.setKnowledgeId(JSONUtil.getString(reJson, "KnowledgeId"));
				recommend.setAnswerSource(JSONUtil.getString(reJson, "AnswerSource"));
				recommendList.add(recommend);
			}
			setRecommendList(recommendList);
		}
	}
	
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Knowledge getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	public List<Recommend> getRecommendList() {
		return recommendList;
	}
	public void setRecommendList(List<Recommend> recommendList) {
		this.recommendList = recommendList;
	}
	@Override
	public String toString() {
		return "Message [text=" + text + ", type=" + type + ", knowledge=" + knowledge + ", recommendList=" + recommendList
				+ "]";
	}
	
}
