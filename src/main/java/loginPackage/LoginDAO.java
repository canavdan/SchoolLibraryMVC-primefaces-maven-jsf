/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginPackage;

import databasePackage.Database;

/**
 *
 * @author Nemesis
 */
public class LoginDAO {
     public static boolean validate(String username, String password) {
		Database database=new Database();
                return database.searchLogin(username, password);
		//return false;
	}
    public static boolean validate2(String username, String password) {
		Database database=new Database();
                return database.searchLogin2(username, password);
		//return false;
	}
     public static String validate3(String username, String password) {
		Database database=new Database();
                if(database.searchLogin3(username, password).equals("go"))
                {
                     return database.searchLogin4(username, password);
                }else
                return database.searchLogin3(username, password);
		//return false;
	}
}
