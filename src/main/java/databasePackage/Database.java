/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasePackage;

import classesPackage.Loan;
import classesPackage.LoanHistory;
import classesPackage.Materials;
import classesPackage.Members;
import classesPackage.Staff;
import com.mycompany.libraryproject.searchBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Nemesis
 */
public class Database implements Serializable {

    Connection con = null;
    Statement statement = null;

    //public 
    public List<Materials> getMaterialsFromDatabase() throws SQLException {
        List<Materials> materials = new ArrayList<Materials>();
       

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM materiel");
            while (result.next()) {
                Materials mat = new Materials();
                
                mat.setMaterielID(result.getInt("materielID"));
                mat.setType_ID(result.getInt("type_ID"));
                mat.setLanguage_ID(result.getInt("language_ID"));
                mat.setPublicher_ID(result.getInt("publicher_ID"));
                mat.setCategories_ID(result.getInt("categories_ID"));
                mat.setBranch_ID(result.getInt("branch_ID"));
                mat.setName(result.getString("name"));
                mat.setTitle(result.getString("title"));
                mat.setPrice(result.getString("price"));
                mat.setPageNo(result.getInt("pageNo"));
                mat.setDescription(result.getString("description"));
                mat.setAvaible(result.getInt("avaible"));
                
                materials.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return materials;
    }

    public List<Materials> getLastestMaterialsFromDatabase() throws SQLException {
        List<Materials> materials2 = new ArrayList<Materials>();
      
        String sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                + "group_concat(au.name ,' ', au.surname) as aut\n"
                + "From materiel m\n"
                + "inner join type t ON t.typeID=m.type_ID\n"
                + "inner join language l ON l.languageID=m.language_ID\n"
                + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                + "inner join branch b ON b.branchID=m.branch_ID\n"
                + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                + "inner join author au ON aus.author_id=au.id\n"
                + "group by materielID order by materielID desc limit 15;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                Materials mat = new Materials();
                mat.setMaterielID(result.getInt("materielID"));
                mat.setName(result.getString("name"));
                mat.setTitle(result.getString("title"));
                mat.setPrice(result.getString("price"));
                mat.setPageNo(result.getInt("pageNo"));
                mat.setDescription(result.getString("description"));
                mat.setAvaible(result.getInt("avaible"));
                mat.setType(result.getString("t.name"));
                mat.setLang(result.getString("l.lang"));
                mat.setPublicher(result.getString("p.name"));
                mat.setBranch(result.getString("b.name"));
                mat.setCategories(result.getString("c.name"));
                mat.setAuthors(result.getString("aut"));

                materials2.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
              
                statement.close();
            }
            if (con != null) {
                
                con.close();
            }
        }

        return materials2;
    }

    public List<String> catalogNames() {
        List<String> catalogNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM type");
            while (result.next()) {
               
                catalogNames.add(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    //  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return catalogNames;
    }
public List<String> categoriesNames() {
        List<String> categoriesNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories");
            while (result.next()) {
               
                categoriesNames.add(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    //  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return categoriesNames;
    }
    public List<String> branchNames() {
        List<String> branchNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM branch");
            while (result.next()) {
               
                branchNames.add(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return branchNames;
    }

    public List<String> langNames() {
        List<String> langNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT lang FROM language");
            while (result.next()) {
              
                langNames.add(result.getString("lang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return langNames;
    }
 public List<String> typeNames() {
        List<String> typeNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM type");
            while (result.next()) {
                
                typeNames.add(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return typeNames;
    }
    public List<Materials> searchSqlCommand(String type, String branch, String lang, String index, String query) {
        List<Materials> mateList = new ArrayList<>();
        String sql;
        /* System.out.println(type);
         System.out.println(branch);
         System.out.println(lang);
         System.out.println(index);*/
        
        if (index.equals("keywords")) {
            if (type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,\n" +
"m.avaible,t.name,l.lang,p.name,c.name,b.name,\n" +
"group_concat(au.name ,' ', au.surname) as aut\n" +
"From materiel m\n" +
"inner join type t ON t.typeID=m.type_ID\n" +
"inner join language l ON l.languageID=m.language_ID\n" +
"inner join publisher p ON p.publisherID=m.publicher_ID\n" +
"inner join categories c ON c.categoriesID=m.categories_ID\n" +
"inner join branch b ON b.branchID=m.branch_ID\n" +
"inner join authors aus ON aus.materiel_materielID=m.materielID\n" +
"inner join author au ON aus.author_id=au.id\n" +
"Where m.title LIKE '%" + query + "%' or m.name LIKE '%" + query + "%' \n" +
"or m.description LIKE '%" + query + "%' or\n" +
"t.name LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n" +
"or p.name LIKE '%" + query + "%'\n" +
"group by m.materielID\n" +
"order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and l.lang='" + lang + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and t.name='" + type + "' group by m.materielID  order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"                 
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,\n" +
"m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n" +
"group_concat(au.name ,' ', au.surname) as aut\n" +
"From materiel m\n" +
"inner join type t ON t.typeID=m.type_ID\n" +
"inner join language l ON l.languageID=m.language_ID\n" +
"inner join publisher p ON p.publisherID=m.publicher_ID\n" +
"inner join categories c ON c.categoriesID=m.categories_ID\n" +
"inner join branch b ON b.branchID=m.branch_ID\n" +
"inner join authors aus ON aus.materiel_materielID=m.materielID\n" +
"inner join author au ON aus.author_id=au.id\n" +
"Where (m.title LIKE '%" + query + "%' or m.name LIKE \n" +
"'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n" +
" LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n" +
"or p.name LIKE '%" + query + "%') and b.name='" + branch + "'\n" +
"and l.lang='" + lang + "'\n" +
"group by m.materielID  order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%' or m.name LIKE \n"
                        + "'%" + query + "%' or m.description LIKE '%" + query + "%' or t.name\n"
                        + "LIKE'%" + query + "%' or c.name LIKE '%" + query + "%'\n"
                        + "or p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' and b.name='" + branch + "'group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            }
        } else if (index.equals("author")) {
           if (type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
           
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and l.lang='" + lang + "' group by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and t.name='" + type + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                      + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and b.name='" + branch + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else {
              
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (au.name LIKE '%" + query + "%' or au.surname LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            }

        } else if (index.equals("title")) {
            if (type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where m.title LIKE '%" + query + "%' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
              
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and l.lang='" + lang + "' group by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and t.name='" + type + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                      + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and b.name='" + branch + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else {
               
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (m.title LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            }
        } else if (index.equals("publisher")) {
            if (type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
              
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"                  
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where p.name LIKE '%" + query + "%' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                //ut.println("All cat and allbranch " + lang);
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and t.name='" + type + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                       
                        + "and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                      + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;

            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and b.name='" + branch + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (p.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' and b.name='" + branch + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            }
        } else//categories
        {
            if (type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where c.name LIKE '%" + query + "%' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
               
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                         + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                        + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and t.name='" + type + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && !branch.equals("allBranch") && lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                       + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else if (!type.equals("allCatalog") && branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID ";
                mateList = searchResult(sql);
                return mateList;
            } else if (type.equals("allCatalog") && !branch.equals("allBranch") && !lang.equals("allLang")) {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                      + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and b.name='" + branch + "'\n"
                        + "and l.lang='" + lang + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            } else {
                sql = "SELECT m.materielID,m.title,m.name,m.price,m.pageNo,m.description,m.avaible,t.name,l.lang,p.name,c.name,b.name,\n"
                        + "group_concat(au.name ,' ', au.surname) as aut\n"
                        + "From materiel m\n"
                        + "inner join type t ON t.typeID=m.type_ID\n"
                        + "inner join language l ON l.languageID=m.language_ID\n"
                        + "inner join publisher p ON p.publisherID=m.publicher_ID\n"
                        + "inner join categories c ON c.categoriesID=m.categories_ID\n"
                        + "inner join branch b ON b.branchID=m.branch_ID\n"
                       + "inner join authors aus ON aus.materiel_materielID=m.materielID\n"
                        + "inner join author au ON aus.author_id=au.id\n"
                        + "Where (c.name LIKE '%" + query + "%') and t.name='" + type + "'\n"
                        + "and l.lang='" + lang + "' and b.name='" + branch + "' group by m.materielID order by m.materielID";
                mateList = searchResult(sql);
                return mateList;
            }
        }
        //return mateList;
    }

    public List<Materials> searchResult(String sql) {

        List<Materials> mateList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Materials mat = new Materials();
                mat.setMaterielID(result.getInt("materielID"));
                mat.setName(result.getString("name"));
                mat.setTitle(result.getString("title"));
                mat.setPrice(result.getString("price"));
                mat.setPageNo(result.getInt("pageNo"));
                mat.setDescription(result.getString("description"));
                mat.setAvaible(result.getInt("avaible"));
                mat.setType(result.getString("t.name"));
                mat.setLang(result.getString("l.lang"));
                mat.setPublicher(result.getString("p.name"));
                mat.setBranch(result.getString("b.name"));
                mat.setCategories(result.getString("c.name"));
                mat.setAuthors(result.getString("aut"));
                mateList.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return mateList;
    }

    public boolean searchLogin(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT memberID,password from members where memberID='" + username + "' and password='" + password + "'";
            /* String sql = "SELECT s.staffID,s.password,\n"
                    + "m.memberID,m.password\n"
                    + "From staff s,members m\n"
                    + "WHERE (m.memberID='" + username + "'\n"
                    + "and m.password='" + password + "') or\n"
                    + "(s.staffID='" + username +"' and s.password='" + password + "')";
             */ ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean searchLogin2(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT staffID,password from staff where staffID='" + username + "' and password='" + password + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return true;
            }
           // searchLogin2(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public String searchLogin3(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "select role from roles where rolesID = (select roles_rolesID FROM members WHERE memberID = '" + username + "')";
            // String sql="SELECT memberID,password from members where memberID='" + username + "' and password='"+ password +"'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return result.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return "go";
    }

    public String searchLogin4(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "select role from roles where rolesID = (select role_ID FROM staff WHERE staffID = '" + username + "')";
            // String sql="SELECT memberID,password from members where memberID='" + username + "' and password='"+ password +"'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return result.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    public Members getUserInfo(String memberID) {
        Members member = new Members();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT m.memberID,m.name,m.surname,m.mail,m.phonenumber,m.adress,\n"
                    + "d.departmentName,m.password,f.nameFaculty\n"
                    + "From members m\n"
                    + "inner join department d ON d.departmentID=m.department_ID\n"
                    + "inner join faculty f ON f.facultyID=m.faculty_ID\n"
                    + "where m.memberID='" + memberID + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                member.setMemberID(result.getInt("m.memberID"));
                member.setName((result.getString("m.name")));
                member.setSurname(result.getString("m.surname"));
                member.setMail(result.getString("m.mail"));
                member.setPhonenumber(result.getString("m.phonenumber"));
                member.setAdress(result.getString("m.adress"));
                member.setDepartmant(result.getString("d.departmentName"));
                member.setFaculty(result.getString("f.nameFaculty"));
                member.setPassword(result.getString("m.password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return member;
    }

    public Staff getStaffInfo(String staffID) {
        Staff staff = new Staff();
          
          int x=Integer.valueOf(staffID);
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT s.staffID,s.name,s.surname,s.mail,\n"
                    + "s.number,s.password,r.role\n"
                    + "FROM staff s\n"
                    + "INNER JOIN roles r ON s.role_ID=r.rolesID\n"
                    + "WHERE s.staffID='"+ x +"'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                 
                staff.setStaffID(result.getInt("s.staffID"));
                staff.setName((result.getString("s.name")));
                staff.setSurname(result.getString("s.surname"));
                staff.setMail(result.getString("s.mail"));
                staff.setPhonenumber(result.getString("s.number"));
                staff.setPassword(result.getString("s.password"));
                staff.setRole(result.getString("r.role"));              
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return staff;
    }

    public List<Loan> getUserMaterials(String memberID) {
        List<Loan> loan = new ArrayList<Loan>();
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT l.loanID,l.member_ID,l.duedate,l.outdate,\n"
                    + "l.extends,m.name\n"
                    + "FROM loan l\n"
                    + "inner join materiel m ON m.materielID=l.materiel_ID\n"
                    + "where l.member_ID='" + memberID + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Loan loanC = new Loan();
                loanC.setLoanID(result.getInt("l.loanID"));
                loanC.setDue(result.getDate("l.duedate"));
                loanC.setOut(result.getDate("l.outdate"));
                loanC.setExtend(result.getInt("l.extends"));
                loanC.setMaterielName(result.getString("m.name"));
                loan.add(loanC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return loan;
    }

    public boolean hasPersonPenalty(String memberIDD) {
       
        int member_ID = Integer.parseInt(memberIDD);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
           
            String sql = "SELECT p.memberID FROM penalty p where p.memberID=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, member_ID);
            ResultSet rs = ps.executeQuery();
            // ResultSet result = statement.executeQuery(sql);

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public void returnBook(Loan l) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            //statement = con.createStatement();
            //String sql = "DELETE FROM loan where loanID='"+ l.getLoanID() +"'";
            String sql = "DELETE FROM loan where loanID=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, l.getLoanID());
            preparedStmt.execute();
            fixAvaible(l.getMaterielName());
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void fixAvaible(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "UPDATE materiel SET avaible='1' where name=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, name);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean hasUserAbletoTake(String memberID) {
       
        int member_ID = Integer.parseInt(memberID);
        int number = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            //String sql = "SELECT p.memberID FROM penalty p where p.memberID='" + member_ID + "'";
            String sql = "Select COUNT(*) From loan WHERE member_ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, member_ID);
            ResultSet rs = ps.executeQuery();
            // ResultSet result = statement.executeQuery(sql);    
            while (rs.next()) {
                number = rs.getInt(1);
                System.out.println("Sahip olunan kitap sayısı");
                System.out.println(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return number >= 3;
    }

    public List<Materials> getAvaibleMaterials(List<Materials> material) {
        List<Materials> material2 = new ArrayList<>();
        for (Materials m : material) {
            if (m.getAvaible() == 1) {
                material2.add(m);
            }
        }
        return material2;
    }

    /* public void takeMaterial(Materials material, String memberID) {
         //setAvaibleZero(material,memberID);
        addtoLoan(material, memberID);
       
    }*/
    public void addtoLoan(Materials material, String memberID) {
        
        int member_ID = Integer.parseInt(memberID);
        Calendar calendar = Calendar.getInstance();
        java.sql.Date outdate = new java.sql.Date(calendar.getTime().getTime());
       // System.out.println(outdate.toString());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, 30);
        java.sql.Date duedate = new java.sql.Date(calendar2.getTime().getTime());
       // System.out.println(duedate.toString());
        String sql = "INSERT INTO `library`.`loan` (`member_ID`, `materiel_ID`, `outdate`, `duedate`, `extends`)"
                + "VALUES (?,?,?,?,?);";
        String sql3 = "INSERT INTO `library`.`loan` (`member_ID`, `materiel_ID`, `outdate`, `duedate`, `extends`) VALUES ('" + memberID + "', '" + material.getMaterielID() + "', '" + outdate + "', '" + duedate + "', '0');";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            statement.executeUpdate(sql3);
            //  statement.executeQuery(sql3);
            // PreparedStatement preparedStmt = con.prepareStatement(sql3);         
            //  preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void setAvaibleZero(Materials material, String memberID) {
       
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "UPDATE materiel SET avaible=? where materielID=?";
          
            //String sql2 = "UPDATE materiel SET avaible='0' where materielID='"+material.getMaterielID()+"'";
            // statement.executeQuery(sql2);
            //  statement.executeUpdate(sql2);
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, 0);
            preparedStmt.setInt(2, material.getMaterielID());
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public PieChartModel typeModel() {
        PieChartModel typeModel = new PieChartModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT t.name,COUNT(*) as count\n"
                    + "FROM materiel m\n"
                    + "INNER JOIN type t ON m.type_ID=t.typeID\n"
                    + "group by t.name";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                typeModel.set(result.getString(1), result.getBigDecimal(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        typeModel.setTitle("Material Types");
        typeModel.setLegendPosition("w");
        return typeModel;
    }

    public PieChartModel langModel() {
        PieChartModel typeModel = new PieChartModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT l.lang,COUNT(*) as count\n"
                    + "FROM materiel m\n"
                    + "INNER JOIN language l ON m.language_ID=l.languageID\n"
                    + "group by l.lang";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                typeModel.set(result.getString(1), result.getBigDecimal(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        typeModel.setTitle("Simple Pie");
        typeModel.setLegendPosition("w");
        return typeModel;
    }

    public PieChartModel catModel() {
        PieChartModel typeModel = new PieChartModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT c.name,COUNT(*) as count\n"
                    + "FROM materiel m\n"
                    + "INNER JOIN categories c ON m.categories_ID=c.categoriesID\n"
                    + "group by c.name";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
               
                typeModel.set(result.getString("c.name"), result.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /*typeModel.setTitle("Simple Pie");
        typeModel.setLegendPosition("w");*/
        typeModel.setLegendPosition("e");
        typeModel.setFill(false);
        typeModel.setShowDataLabels(true);
        typeModel.setDiameter(150);
        return typeModel;
    }

    public PieChartModel branchModel() {
        PieChartModel typeModel = new PieChartModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT b.name,COUNT(*) as count\n"
                    + "FROM materiel m\n"
                    + "INNER JOIN branch b ON m.branch_ID=b.branchID\n"
                    + "group by b.name";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                typeModel.set(result.getString(1), result.getBigDecimal(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        typeModel.setTitle("Simple Pie");
        typeModel.setLegendPosition("w");
        return typeModel;
    }

    public List<Members> getMembersSearch(String query) {
       
        List<Members> member = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            String sql = "SELECT m.memberID,m.name,m.surname,m.password,d.departmentName,f.nameFaculty,m.mail,\n"
                    + "m.phonenumber,m.adress\n"
                    + "FROM members m\n"
                    + "INNER JOIN department d ON m.department_ID=d.departmentID\n"
                    + "INNER JOIN faculty f ON f.facultyID=m.faculty_ID\n"
                    + "WHERE m.memberID LIKE'%" + query + "%' \n"
                    + "or m.name LIKE'%" + query + "%' or m.surname LIKE '%" + query + "%'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Members mem = new Members();
                mem.setName(result.getString("m.name"));
                mem.setSurname(result.getString("m.surname"));
                mem.setAdress(result.getString("m.adress"));
                mem.setFaculty(result.getString("f.nameFaculty"));
                mem.setDepartmant(result.getString("d.departmentName"));
                mem.setMail(result.getString("m.mail"));
                mem.setMemberID(result.getInt("m.memberID"));
                mem.setPassword(result.getString("m.password"));
                mem.setPhonenumber(result.getString("m.phonenumber"));
              
                member.add(mem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return member;
    }
    public int debtAmount(String memberID){
         int member_ID = Integer.parseInt(memberID);
         
         int amount=0;
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
      
            String sql = "SELECT p.memberID,p.duedate FROM penalty p where p.memberID=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, member_ID);
            ResultSet rs = ps.executeQuery();
            // ResultSet result = statement.executeQuery(sql);

            while (rs.next()) {
               date=rs.getDate("p.duedate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         Date date2 = new Date();
         int diffInDays = (int)( (date2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24) );
         amount=(diffInDays*75)/100;
        return amount;
    }
    public void clearDebt(String memberID){
        
         int member_ID = Integer.parseInt(memberID);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
           
            String sql = "DELETE FROM penalty WHERE memberID=?";
           PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, member_ID);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            // ResultSet result = statement.executeQuery(sql);

           // while (rs.next()) {
             
           // }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
    public void addBook(String name,String title,String descriptin,String type,String categories,
            String lang,String branch,int price,int pageNo,int materialID,String publicher)
     {
        
  String sql = "INSERT INTO `library`.`materiel` (`materielID`, `type_ID`, `language_ID`, \n" +
                "`publicher_ID`, `categories_ID`, `branch_ID`, `name`, `title`, `price`,\n" +
                " `pageNo`, `description`, `avaible`)\n" +
                " VALUES (?,(SELECT typeID FROM library.type\n" +
                "WHERE name=?),(SELECT languageID FROM library.language\n" +
                "where lang=?),(SELECT publisherID FROM library.publisher\n" +
                "WHERE name=?),(SELECT categoriesID FROM library.categories\n" +
                "where name=?),(SELECT branchID FROM library.branch\n" +
                "where name=?),?,?,?,?,?,?);";
             try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();          
            //  statement.executeQuery(sql3);
             PreparedStatement preparedStmt = con.prepareStatement(sql);  
             preparedStmt.setInt(1, materialID);
             preparedStmt.setString(2, type);
              preparedStmt.setString(3, lang);
               preparedStmt.setString(4, publicher);
                preparedStmt.setString(5, categories);
                 preparedStmt.setString(6, branch);
                  preparedStmt.setString(7, name);
                   preparedStmt.setString(8, title);
                   preparedStmt.setInt(9, price);
                   preparedStmt.setInt(10, pageNo);
                    preparedStmt.setString(11, descriptin);
                    preparedStmt.setInt(12,1);
             preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    public void changePassword(String password,String memberID) {
        int member_ID=Integer.valueOf(memberID);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "UPDATE members SET password=? where memberID=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, password);
              preparedStmt.setInt(2, member_ID);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
     public void addRequest(String subject,String book) {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "INSERT INTO `library`.`request` \n" +
                        "(`subject`, `book`) VALUES (?,?);";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, subject);
            preparedStmt.setString(2, book);
             
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /* public int getExtendNumber(){
         
     }*/
     public int canIncrease(int loanID){
         int extendNo=0;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "SELECT extends FROM loan where loanID=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, loanID);
             ResultSet rs = preparedStmt.executeQuery();
            //preparedStmt.execute();
            while(rs.next()){
                extendNo=rs.getInt("extends");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         return 0;
      
     }
     public void increaseExtend(int loanID,int incNumber,java.sql.Date dueD){
 
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "UPDATE loan SET extends=?,duedate=? where loanID=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, incNumber);
            preparedStmt.setDate(2, dueD);
             preparedStmt.setInt(3, loanID);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
     }
     public void addLoanHistory(Loan loan){
            System.out.println("Girdi +" + loan.getLoanID());
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            // UPDATE `library`.`materiel` SET `avaible`='0' WHERE `materielID`='5';
            String sql = "INSERT INTO `library`.`loanhistory`\n" +
"(`member_ID`, `materiel_ID`) VALUES ((Select l.member_ID\n" +
"From loan l\n" +
"where l.loanID=?),(SELECT l.materiel_ID\n" +
"From loan l\n" +
"where l.loanID=?));";
                     String sql2 = "INSERT INTO `library`.`loanhistory`\n" +
"(`member_ID`, `materiel_ID`) VALUES ((Select l.member_ID\n" +
"From loan l\n" +
"where l.loanID='"+loan.getLoanID()+"'),(SELECT l.materiel_ID\n" +
"From loan l\n" +
"where l.loanID='"+loan.getLoanID()+"'));";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, loan.getLoanID());
            preparedStmt.setInt(2, loan.getLoanID());
             //statement.executeQuery(sql2);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     }
     public List<LoanHistory> getHistoryM(String userID){
         
         List<LoanHistory> materials=new ArrayList<>();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery("Select lh.historyID,ma.name\n" +
"From loanhistory lh\n" +
"INNER JOIN materiel ma ON lh.materiel_ID=ma.materielID\n" +
"INNER JOIN members me ON lh.member_ID=me.memberID\n" +
"where me.memberID='"+userID +"'");
            while (result.next()) {
                LoanHistory mat = new LoanHistory();
                mat.setLoanHistoryID(result.getInt("lh.historyID"));
                mat.setName(result.getString("ma.name"));
                materials.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return materials;
     }
     public void controlDuetoDate(){
          Date date = new Date();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            //System.out.println(memberIDD);
            //String sql = "SELECT p.memberID FROM penalty p where p.memberID='" + member_ID + "'";
            String sql = "SELECT * FROM loan l";
            ResultSet rs = statement.executeQuery(sql);
            // ResultSet result = statement.executeQuery(sql);
            while (rs.next()) {

                if (!hasPenalt(rs.getInt("materiel_ID"))) {
                } else {
                    date = rs.getDate("l.duedate");
                    Date date2 = new Date();
                    if (!date.after(date2)) {
                        addPenalty(rs.getInt("member_ID"), rs.getInt("materiel_ID"), rs.getDate("duedate"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

     }
     public boolean hasPenalt(int matID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            //System.out.println(memberIDD);
            //String sql = "SELECT p.memberID FROM penalty p where p.memberID='" + member_ID + "'";
            String sql = "SELECT * FROM penalty where materialID='" + matID + "'";
            ResultSet rs = statement.executeQuery(sql);
            // ResultSet result = statement.executeQuery(sql);
            while (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
     public void addPenalty(int memID, int matID, java.sql.Date dueD) {
        System.out.println(memID + " " + matID + " " + dueD.toString());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "admin");
            statement = con.createStatement();
            //System.out.println(memberIDD);
            //String sql = "SELECT p.memberID FROM penalty p where p.memberID='" + member_ID + "'";
            String sql = "INSERT INTO `library`.`penalty`\n"
                    + " (`memberID`, `materialID`, `duedate`) \n"
                    + " VALUES (?,?,?);";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, memID);
            preparedStmt.setInt(2, matID);
            preparedStmt.setDate(3, dueD);           
            preparedStmt.execute();
            //ResultSet rs = statement.executeQuery(sql);
            // ResultSet result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
