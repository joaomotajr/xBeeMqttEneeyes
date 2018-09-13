package service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import model.Position;
import xbee.MainApp;

public class JsonService {
		
	private static String directoryBase = JsonService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private JSONObject jsonObject = new JSONObject();
	private JSONParser jsonParser  = new JSONParser();
	
	
	private String nameFile;
	
	public void createJackson() {
	
	}
	
	@SuppressWarnings("unchecked")
	public void save(Position position) {		
		
		try {
			Class<?> clazzOrigem = position.getClass();
			
			for (Field field : clazzOrigem.getDeclaredFields()) {
				field.setAccessible(true);
				jsonObject.put(field.getName(),  field.get(position));	        
			}	
			
			
			File file = getFile();		
			FileWriter fileWriter = new FileWriter(file);			
			
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.close();
			
			System.out.println("Successfully Copied JSON Object to File...");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			MainApp.logger.error(e.getMessage());
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void create(List<Position> positions) {
		
		JsonArray jsonArray = new JsonArray();
		
		try {					
			
			for (Position position : positions) {
						
				Class<?> clazzOrigem = position.getClass();
		
				Map<String, Object> item = new HashMap<String, Object>();
				
				for (Field field : clazzOrigem.getDeclaredFields()) {
					field.setAccessible(true);
					item.put(field.getName(),  field.get(position));
				}
							
				jsonArray.add(JSONValue.toJSONString(item));				
			}								
			
			JSONObject my_obj = new JSONObject();
			my_obj.put("Sensors", jsonArray);
			
			File file = getFile();		
			FileWriter fileWriter = new FileWriter(file);			
			
			fileWriter.write(my_obj.toJSONString());
			fileWriter.close();
			
			System.out.println("Successfully Copied JSON Object to File...");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			MainApp.logger.error(e.getMessage());
		}
		
		
	}
	
	public void update(Position position) {
		
		try {
		
			File file = getFile();				
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
			
			JSONArray msg = (JSONArray) jsonObject.get("Sensors");
            Iterator<String> iterator = msg.iterator();
            
            Map<String,String> resultMap = new HashMap<String,String>();
            ObjectMapper mapperObj = new ObjectMapper();                        
            
            resultMap = mapperObj.readValue(msg.get(0).toString(), new TypeReference<HashMap<String,String>>(){});
              
            while (iterator.hasNext()) {            	            	           	
                System.out.println(iterator.next());                
            }						
		
		}
		catch (Exception e) {
			MainApp.logger.error("Erro na leitura do arquivo (Json), verifique ::" + e.getMessage());
		}
		
	}
		
	public void createFile() {	
				
		try {			
			
			File file = getFile();
			
			if(!file.exists()){
			   file.createNewFile();
			}
		} catch (Exception e) {
			MainApp.logger.error("Erro criação do arquivo (Json), verifique ::" + e.getMessage());
		}		
	}	

	public File getFile() {
		
		String decodedPath = null;
		File file = null; 
		
		try {
			decodedPath = URLDecoder.decode(directoryBase, "UTF-8");
			
			file = new File(decodedPath + this.nameFile);
						
		} catch (Exception e) {
			
			MainApp.logger.error("Erro obtenção do arquivo (Json), verifique ::" + e.getMessage());						
		}
		
		return file;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}	

}
