package com.xs.aliet.beebot.request;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 公共请求头对象
 * @author 小帅丶
 */
public class GeneralRequest {
	@JSONField(name="Format")
	private String Format;
	@JSONField(name="Version")
	private String Version;
	@JSONField(name="AccessKeyId")
	private String AccessKeyId;
	@JSONField(name="Signature")
	private String Signature;
	@JSONField(name="SignatureMethod")
	private String SignatureMethod;
	@JSONField(name="Timestamp")
	private String Timestamp;
	@JSONField(name="SignatureVersion")
	private String SignatureVersion;
	@JSONField(name="SignatureNonce")
	private String SignatureNonce;

	public GeneralRequest(String format, String version, String accessKeyId,
			String signature, String signatureMethod, String timestamp,
			String signatureVersion, String signatureNonce) {
		super();
		Format = format;
		Version = version;
		AccessKeyId = accessKeyId;
		Signature = signature;
		SignatureMethod = signatureMethod;
		Timestamp = timestamp;
		SignatureVersion = signatureVersion;
		SignatureNonce = signatureNonce;
	}
	public GeneralRequest(String accessKeyId,String timestamp,String signatureNonce) {
		super();
		Format = "JSON";
		Version = "2017-10-11";
		AccessKeyId = accessKeyId;
		SignatureMethod = "HMAC-SHA1";
		Timestamp = timestamp;
		SignatureVersion = "1.0";
		SignatureNonce = signatureNonce;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getAccessKeyId() {
		return AccessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		AccessKeyId = accessKeyId;
	}
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getSignatureMethod() {
		return SignatureMethod;
	}
	public void setSignatureMethod(String signatureMethod) {
		SignatureMethod = signatureMethod;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public String getSignatureVersion() {
		return SignatureVersion;
	}
	public void setSignatureVersion(String signatureVersion) {
		SignatureVersion = signatureVersion;
	}
	public String getSignatureNonce() {
		return SignatureNonce;
	}
	public void setSignatureNonce(String signatureNonce) {
		SignatureNonce = signatureNonce;
	}
	
}
