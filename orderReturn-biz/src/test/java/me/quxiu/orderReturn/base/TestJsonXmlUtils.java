package me.quxiu.orderReturn.base;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/*import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;*/

/**
 * @author 	vivian.chen
 * @version 2015-07-28
 * */
public class TestJsonXmlUtils {
	/**
	 * com.fasterxml.jackson
	 * 
	 * 2.* 版本
	 *
	 * @param object
	 */
	public static void printObject(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json;
		try {
			json = mapper.writeValueAsString(object);
			System.out.println(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * org.codehaus.jackson
	 * 
	 * 1.*版本
	 * 
	 * @param object
	 */
	public static void printObject2(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		//mapper.configure(Feature.INDENT_OUTPUT, true);
		String json;
		try {
			json = mapper.writeValueAsString(object);
			System.out.println(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
