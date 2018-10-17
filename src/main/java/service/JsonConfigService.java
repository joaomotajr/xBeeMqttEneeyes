package service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Config;
import xbee.MainApp;

public class JsonConfigService {
		
	private static String directoryBase = JsonConfigService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static String directoryDB = "DB";
	
	private String nameFile;
	
	public JsonConfigService() {		
	}
	
	public JsonConfigService(String nameFile) {
		
		this.setNameFile(nameFile);
		this.getFile();	
	}
		
	public List<Config> read() {
		
		File file = getFile();
		return fromJsonArray(file);    		
	}
		
	private List<Config> fromJsonArray(File file) {
		
        JSONParser jsonParser = new JSONParser();
        ObjectMapper mapperObj = new ObjectMapper();
        List<Config> positions = new ArrayList<Config>();
        
        try {       
        	
        	FileReader reader = new FileReader(file);
        			
            Object obj = jsonParser.parse(reader);            
            JSONArray positionList = (JSONArray) obj;            
            
            positions = mapperObj.readValue(positionList.toJSONString(), new TypeReference<List<Config>>(){});      
             
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

	public File getFile() {		
		
		File file = null;
				
		try {			
			
			file = new File(getPath() + File.separatorChar + this.nameFile);
						
		} catch (Exception e) {
			
			MainApp.logger.error("JS Service - Get File :: " + e.getMessage());						
		}
		
		return file;
	}
	
	private String getPath() throws UnsupportedEncodingException {
		
		String decodedPath = URLDecoder.decode(directoryBase, "UTF-8");
		File baseDir = new File(decodedPath);
		
		File dir = new File(baseDir.getParent().toString() + File.separatorChar + directoryDB);
						
		if(!dir.exists())
			dir.mkdir();
			
		String pathFile = dir.toString();	
		
		return pathFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

}
