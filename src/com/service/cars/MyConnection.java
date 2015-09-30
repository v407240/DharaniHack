package com.service.cars;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyConnection {
// JDBC driver name and database URL
static final String PASS = "mycatsql";
private Connection conn ;
private Statement stmt ;
public String sqlString ;

public MyConnection(String sql){
	sqlString = sql;
}
protected void finalize ()  {
	try {
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  try {
		conn.close();
	} catch (SQLException e) {

		e.printStackTrace();
	}
}
public java.util.ArrayList<HashMap<String,String>> runSql() {
	  //STEP 2: Register JDBC driver
	ResultSet rs = null;
	java.util.ArrayList<HashMap<String,String>> myList = 
			new ArrayList<HashMap<String, String>>();
		try {
			PropReader mpc = new PropReader("db");
			Class.forName(mpc.getPropertyValue("db.driver"));
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(mpc.getPropertyValue("db.url") 
					+ "://" + mpc.getPropertyValue("db.host")
					+ "/" + mpc.getPropertyValue("db.name"), 
					mpc.getPropertyValue("db.user"), PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sqlString);
			  ResultSetMetaData rsmd = rs.getMetaData();
			  int columnsNumber = rsmd.getColumnCount();
			  
			  
			  while (rs.next()) {
			  	HashMap<String,String> rows = new HashMap<String,String>();
			      for (int i = 1; i <= columnsNumber; i++) {
			          if (i > 1) System.out.print(",  ");
			          String columnValue = rs.getString(i);
			          System.out.print(columnValue + " " + rsmd.getColumnName(i));
			          rows.put(rsmd.getColumnName(i), columnValue);
			      }
			      myList.add(rows);
			      System.out.println("");
			  }
			  
			  System.out.println("O/p" + myList.toString());

		}catch(SQLException se){
			  //Handle errors for JDBC
			  se.printStackTrace();
			}catch(Exception e){
			  //Handle errors for Class.forName
			  e.printStackTrace();
			}finally{
			  //finally block used to close resources
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }// nothing we can do
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
			  }//end finally try

	return myList;
}

public static void main(String[] args) {

try {
  String tsql = "SELECT * FROM user";
  MyConnection conn = new MyConnection(tsql);
  java.util.ArrayList<HashMap<String,String>> myList = conn.runSql();
  
  System.out.println("O/p" + myList.toString());
  
  //STEP 6: Clean-up environment
  
}catch(Exception e){
  //Handle errors for Class.forName
  e.printStackTrace();
}

}//end main

}//end MyConnection