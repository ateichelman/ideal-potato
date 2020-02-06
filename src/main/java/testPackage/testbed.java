/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import com.mycompany.propertyreader.FormProperties;

/**
 *
 * @author ateichelman
 */
public class testbed {
    public static void main(String[] args){
        FormProperties fp = new FormProperties();
        
        String[] names = fp.getMandatoryNames();
        
        System.out.println("First mandatory element name: " + names[0]);
        
        System.out.println("First mandatory element type: " + fp.getType(names[0]));
    }
}
