package com.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Util para el manejo de Json
 * @author Ing Diego Barrera Sotelo
 * @Company OrusSystem
 */
public class UtilJson {

	public static String XmltoJsonString(String xml) throws Exception{
		ObjectMapper xmlMapper = new XmlMapper();
		JsonNode node = xmlMapper.readTree(xml.getBytes());
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.writeValueAsString(node);
	}

}
