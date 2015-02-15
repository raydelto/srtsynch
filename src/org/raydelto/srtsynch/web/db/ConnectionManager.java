package org.raydelto.srtsynch.web.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;

public class ConnectionManager {
	private static ConnectionManager instance;	
	private ConnectionManager() {
	}

	public synchronized static ConnectionManager getInstance() {
		return instance == null ? instance = new ConnectionManager() : instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try{
			InitialContext ctx = new InitialContext();
			com.sun.appserv.jdbc.DataSource ds = (com.sun.appserv.jdbc.DataSource) ctx.lookup("jdbc/srtsynch");
			connection = ds.getConnection();
			ds.getConnection(connection);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public ResultSet getQuery(String sql){
		ResultSet result = null;
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void execute(String sql){		
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
