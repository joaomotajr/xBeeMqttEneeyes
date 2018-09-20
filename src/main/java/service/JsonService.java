package service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Position;
import xbee.MainApp;

public class JsonService {
		
private static String directoryBase = JsonService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	
	private String nameFile;
	
	public JsonService() {		
	}
	
	public JsonService(String nameFile) {
		
		this.setNameFile(nameFile);
		this.createFile();	
	}
	
	public void update(Position position) {
		
		File file = getFile();	
		List<Position> positions = fromJsonArray(file);
		
		if (find(position)) {
			
			int index = positions.indexOf(position); 
			
			positions.set(index, position);						
		}
		else {
			positions.add(position);
		}
		
		save(positions);
		MainApp.logger.info("JS Service - Updated :: " + position.getNome());  
	}
	
	public void save(List<Position> positions) {
		
		JSONArray positionList =  fromPositionToJsonArray(positions);		
		saveFile(positionList.toJSONString());
	}
	
	public List<Position> read() {
		
		File file = getFile();
		return fromJsonArray(file);
    		
	}
	
	public Boolean find(Position position) {
		
		File file = getFile();		
		List<Position> positions = fromJsonArray(file);
		
		return positions.contains(position);		
	} 
	
	@SuppressWarnings("unchecked")
	private JSONArray fromPositionToJsonArray(List<Position> positions) {
		
		JSONObject jsonPosition = new JSONObject();		
		JSONArray positionList = new JSONArray();
		
		try {					
			
			for (Position position : positions) {
						
				Class<?> clazzOrigem = position.getClass();				
				for (Field field : clazzOrigem.getDeclaredFields()) {
					field.setAccessible(true);					
					jsonPosition.put(field.getName(),  field.get(position));
				}							

				positionList.add(jsonPosition);
			}						
			
		}
		catch (Exception e) {
			MainApp.logger.error(e.getMessage());
		}
		
		return positionList;	
	}	
	
	private List<Position> fromJsonArray(File file) {
		
        JSONParser jsonParser = new JSONParser();
        ObjectMapper mapperObj = new ObjectMapper();
        List<Position> positions = new ArrayList<Position>();
        
        try {       
        	
        	FileReader reader = new FileReader(file);
        			
            Object obj = jsonParser.parse(reader);            
            JSONArray positionList = (JSONArray) obj;            
            
            positions = mapperObj.readValue(positionList.toJSONString(), new TypeReference<List<Position>>(){});      
             
        } catch (Exception e) {
        	MainApp.logger.error("JS Service - Convert to Positon :: " + e.getMessage());        
        }
        
        return positions;
	}
	
	public void saveFile(String jsonString) {
		
		try {	
			File file = getFile();		
			FileWriter fileWriter = new FileWriter(file);			
			
			fileWriter.write(jsonString);
			fileWriter.close();
						
		}
		catch (Exception e) {
			MainApp.logger.error("JS Service - Save File :: " + e.getMessage());
		}		
	}
		
	public void createFile() {	
				
		try {			
			
			File file = getFile();
			
			if(!file.exists()){
			   file.createNewFile();
			}
		} catch (Exception e) {
			MainApp.logger.error("JS Service - Create File :: " + e.getMessage());
		}		
	}	

	public File getFile() {
		
		String decodedPath = null;
		File file = null; 
		
		try {
			decodedPath = URLDecoder.decode(directoryBase, "UTF-8");			
			file = new File(decodedPath + this.nameFile);
						
		} catch (Exception e) {
			
			MainApp.logger.error("JS Service - Get File :: " + e.getMessage());						
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
