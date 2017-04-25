/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Nemesis
 */
public class PropertiesC {
    
    public String getUsername(){
          java.util.Properties prop = new java.util.Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		//System.out.println(prop.getProperty("username"));


	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return prop.getProperty("username");
    }
}
