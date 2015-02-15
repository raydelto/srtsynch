/**
 * @author Raydelto
 * Stores basic data of the web app usage
 */
package org.raydelto.srtsynch.web.db;

public class DbLogHandler {
	private static DbLogHandler instance;
	private ConnectionManager con;
	private DbLogHandler() {
		con = ConnectionManager.getInstance();
	}

	public synchronized static DbLogHandler getInstance() {
		return instance == null ? instance = new DbLogHandler() : instance;
	}
	
	public void write(String fileName,String ip, int status){
		con.execute("insert into srtsynch.log(filename,datetime,ip, status) values('"+ fileName+"', now(),'" +  ip+"'," + status + ")");
	}
}
