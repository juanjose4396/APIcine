package com.orussystem.test;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		
		String appConfigRequest = "{\"pf\":{\"banco\":\"0000\",\"canal\":\"MON\",\"codigotrs\":\"920000\",\"date\":\"ddmmyyyy\",\"idServices\":\"01\",\"version\":\"0.1\"},\"pv\":{\"email\":\"diego@orussystem.com\",\"imei\":\"321654987\",\"numeroCelular\":\"3142519637\"}}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode actualObj = mapper.readTree(appConfigRequest);
		
		System.out.println(actualObj.get("pv").get("numeroCelular"));
	}
}
