package com.grepper.utils;

import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.grepper.model.Entity;

public class JsonHelper {

	public Object unmarshall(String jsonString) {
		if(jsonString == null) return null;
		
		JSONObject jsonObject = null;
		try {

			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(jsonString);
			
			Entity entity = null;
			convertToEntity(entity, jsonObject);
			return entity;
			
			// get a String from the JSON object
/*			String firstName = (String) jsonObject.get("firstname");
			System.out.println("The first name is: " + firstName);

			// get a number from the JSON object
			long id = (Long) jsonObject.get("id");
			System.out.println("The id is: " + id);

			// get an array from the JSON object
			JSONArray lang = (JSONArray) jsonObject.get("languages");

			// take the elements of the json array
			for (int i = 0; i < lang.size(); i++) {
				System.out.println("The " + i + " element of the array: "
						+ lang.get(i));
			}
			Iterator i = lang.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("language " + innerObj.get("lang")
						+ " with level " + innerObj.get("knowledge"));
			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: "
					+ structure.get("name"));
*/
		}catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public String marshall(Object obj){
		
		if(obj == null)return null;
		
		StringWriter out = new StringWriter();
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.writeJSONString(out);
		}catch(Exception e){
			
		}
		return out.toString();
	}
	
	private void convertToEntity(Entity entity, JSONObject obj){
		
		if(entity == null) entity = new Entity();
		entity.setDate((Date)obj.get("date"));
		entity.setDescription((String)obj.get("description"));
		entity.setImageUrl((String)obj.get("image"));
		entity.setLink((String)obj.get("link"));
		entity.setPid((Long)obj.get("pid"));
		entity.setPrice((Double)obj.get("price"));
		entity.setStore((String) obj.get("store"));
		entity.setTitle((String)obj.get("title");
	}
}
