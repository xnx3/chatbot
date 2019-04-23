package com.xs.aliet.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 阿里计算Sign、UTC时间 等公共方法
 * @author 小帅丶
 *
 */
public class AliETSignUtil {
	 private static final String ENCODING = "UTF-8";
    /**
	 * 将java Date格式转成Solr支持的UTC时间
	 * @param date
	 * @return
	 */
	public static String getSolrDate(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
		String result = sdf1.format(date) + "T" + sdf2.format(date) + "Z";
		return result;
	}
	public static HashMap<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }        
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
    				if (key != null && key != "") {
    					key = key.substring(0, 1).toUpperCase() + key.substring(1);
    				}
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }
    public static HashMap<String, Object> convertToMap(Object obj) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }
	/**
	 * SIGN签名生成算法-JAVA版本
	 * @param method 请求方式 GET | POST
	 * @param HashMap<String,String> params 请求参数集，所有参数必须已转换为字符串类型
	 * @param akSecret Access Key Secret
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(String method,HashMap<String,Object> params,String akSecret) throws IOException {
	        // 先将参数以其参数名的字典序升序进行排序
	        Map<String, Object> sortedParams = new TreeMap<>(params);
	        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
	        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	        StringBuilder CanonicalizedQueryString = new StringBuilder();
	        for (Map.Entry<String, Object> param : entrys) {
	            //sign参数 和 空值参数 不加入算法
	            if(param.getValue()!=null && !"".equals(param.getKey().trim()) && !"Signature".equals(param.getKey().trim()) && !"".equals(param.getValue().toString().trim())) {
	            	CanonicalizedQueryString.append(param.getKey().trim()).append("=").append(URLEncoder.encode(param.getValue().toString().trim(),ENCODING)).append("&");
	            }
	        }
	        String signStr = method+"&"+percentEncode("/")+"&"+percentEncode(CanonicalizedQueryString.toString().substring(0, CanonicalizedQueryString.toString().length()-1));
	        System.out.println("加密的参数字符串："+signStr);
	        // 使用MD5对待签名串求签
	        try {
	        	String sign = AESDecode.HMACSha1(signStr,akSecret+"&");
	        	return sign;
	        } catch (Exception ex) {
	            throw new IOException(ex);
	        }
	    }
	/**
	 * 再次处理。适用于阿里云接口
	 * @param value 需要URLEncoder的参数
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	private static String percentEncode(String value) throws UnsupportedEncodingException {
		return value != null ? URLEncoder.encode(value, ENCODING)
				.replace("+", "%20").replace("*", "%2A").replace("%7E", "~")
				: null;
	}
	/**
	 * 获取拼接的参数
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String getParams(HashMap<String,Object> params) throws IOException {
		//  先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            //sign参数 和 空值参数 不加入算法
			if (param.getValue() != null && !"".equals(param.getKey().trim())
					&& !"".equals(param.getKey().trim())
					&& !"".equals(param.getValue().toString().trim())) {
				baseString
						.append(param.getKey().trim())
						.append("=")
						.append(URLEncoder.encode(param.getValue().toString().trim(), ENCODING)).append("&");
			}
        }
       return baseString.deleteCharAt(baseString.length()-1).toString();
    }
}
