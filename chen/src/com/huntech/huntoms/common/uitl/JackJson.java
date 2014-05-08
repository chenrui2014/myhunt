package com.huntech.huntoms.common.uitl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**一些转换方法*/
public class JackJson {
	private static final Logger logger = Logger.getLogger(JackJson.class);
	/** 格式化时间的string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * jackjson把json字符串转换为Java对象的实现方法
	 * return JackJson.fromJsonToObject(this.answersJson, new
	 * TypeReference<List<StanzaAnswer>>(){});
	 * 
	 * @param <T>转换为的java对象
	 * @param jsonjson字符串
	 * @param typeReferencejackjson自定义的类型
	 * @return 返回Java对象
	 */
	public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转换为java对象
	 * return JackJson.fromJsonToObject(this.answersJson, JackJson.class);
	 * 
	 * @param <T>要转换的对象
	 * @param json字符串
	 * @param valueType对象的class
	 * @return 返回对象
	 */
	public static <T> T fromJsonToObject(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("fromJsonToObject(String, TypeReference<T>)", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * java对象转换为json字符串
	 * @param object Java对象
	 * @return 返回字符串
	 */
	public static String fromObjectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("fromObjectToJson(Object)", e);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("fromObjectToJson(Object)", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("fromObjectToJson(Object)", e);
			e.printStackTrace();
		}
		return null;
	}
}
