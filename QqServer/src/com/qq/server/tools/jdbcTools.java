/*
*功能：jdbc的规范功能包
*作者：李际明
*/
package com.qq.server.tools;
import java.sql.*;
public final class jdbcTools 
{
	private static String url = "jdbc:mysql://localhost:3306/qq_server";
	private static String user = "root";
	private static String password = "0816";
	static 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,user,password);
	}
	public static  void free(ResultSet rs,Statement st,Connection conn)
	{
		try
		{
			if(rs!=null)
				rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(st!=null)
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			finally
			{
				if(conn!=null)
					try 
				    {
						conn.close();
					} 
				   catch (SQLException e) 
				    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
		}	
	}	
}