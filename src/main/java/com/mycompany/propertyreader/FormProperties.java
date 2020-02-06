/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertyreader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ateichelman
 */
public class FormProperties {
    /**
     * Map of mandatory element Names and Types
     */
    private HashMap<String, String> mandatoryElements = new HashMap<String, String>();

    /**
     * Map of mandatory element Names and Labels
     */
    private HashMap<String, String> mandatoryLabels = new HashMap<String, String>();
    
    // List of inputs to recieve a userlist
    private List<String> dataListTargets = new ArrayList<>();
    
    private JSONParser parser = new JSONParser();
    private String defaultPropFilename = "formProperties.json"; 
    private String defaultPropPath = "";
    
    public FormProperties(){
        // Init default path.
        this.defaultPropPath = this.getDefaultPath();
        // Load JSON
        this.loadJSONProperties(defaultPropPath);
    }
    
    public void loadJSONProperties(String path){
        try{
            Object obj = parser.parse(new FileReader(path));
            
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray mFields = (JSONArray) jsonObject.get("mandatoryFields");
            
            for(int i = 0; i<mFields.size(); i++){
                
                JSONObject entry = (JSONObject) mFields.get(i);
                
                this.mandatoryElements.put(entry.get("name").toString(), entry.get("type").toString());
                
                this.mandatoryLabels.put(entry.get("name").toString(), entry.get("label").toString());
                
            }
            
            System.out.println(this.mandatoryElements.toString());
            
            System.out.println(this.mandatoryLabels.toString());

            JSONArray dlTargets = (JSONArray) jsonObject.get("userListTargets");
            
            for(int a = 0; a<dlTargets.size(); a++){
                
                JSONObject target = (JSONObject) dlTargets.get(a);
                
                this.dataListTargets.add(target.get("target").toString());
            }
            
            System.out.println(this.dataListTargets.toString());
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String getLabel(String name){
        
        // Return the label associated with the name arg in our labels hash map.
        
        return this.mandatoryLabels.get(name);
    }
    
    public String getType(String name){
        
        // Return the type associated with the name arg in our elements hash map.
        
        return this.mandatoryElements.get(name);
    }
    
    public String[] getMandatoryNames(){
        
        return this.mandatoryElements.keySet().toArray(new String[this.mandatoryElements.size()]);
    }
    
    /**
     * Get path to Default Property File
     * @return 
     */
    private String getDefaultPath(){
        
        String filepath = new File("").getAbsolutePath();
        
        System.out.println(filepath);
        
        filepath += "\\src\\main\\java\\resources\\";
        
        System.out.println(filepath);
        
        filepath += this.defaultPropFilename;
        
        System.out.println(filepath);
        
        return filepath;
    }

}
