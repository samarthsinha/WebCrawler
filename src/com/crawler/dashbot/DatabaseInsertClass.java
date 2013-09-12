package com.crawler.dashbot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInsertClass {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	public void readDataBase(DataWrapperIndexClass ob) throws Exception{
		try{
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost/dashbotdb?" + "user=root&password=");
				
//				statement = connect.createStatement();
//				resultSet=statement.executeQuery("Select * from web_indexed");
//				writeResultSet(resultSet);
				
				 preparedStatement = connect.prepareStatement("insert into web_indexed values (default, ?, ?, ?, ? )");
				 preparedStatement.setString(1, ob.getUrl());
				 preparedStatement.setString(2, ob.getKeywords());
				 preparedStatement.setString(3, ob.getTitle());
				 preparedStatement.setString(4, ob.getDescr());
				 preparedStatement.executeUpdate();
				
		
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			close();
		}
	}
	 private static void writeResultSet(ResultSet resultSet) throws SQLException {
		    while (resultSet.next()) {
		      String uid = resultSet.getString("id");
		      String url = resultSet.getString("url");
		      String keywords= resultSet.getString("keywords");
		      String desc = resultSet.getString("description");
		      String title = resultSet.getString("title");
		     // String name = resultSet.getString("name");
		      System.out.println("id: " + uid);
		      System.out.println("url: " + url);
		      System.out.println("key: " + keywords);
		      System.out.println("tite: "+ title);
		      System.out.println("desc: "+desc);
		    }
		  }
	 private static void close() {
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {

		    }
		  }
}
