/**
 * @author Raydelto
 */

package org.raydelto.srtsynch.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.raydelto.srtsynch.srt.SrtSynchronizer;
import org.raydelto.srtsynch.srt.Variation;
import org.raydelto.srtsynch.web.db.DbLogHandler;

@WebServlet("/Syncrhonize")
@MultipartConfig(location=".")
public class SyncrhonizerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return "subtitles.srt";
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}
	
	/*Raydelto: We had to do this additional step because we have Glassfish 3.1.1 in our server.  
	 * Since Glassfish 3.1.2 this hack is not necessary*/ 
	private String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	}	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLogHandler log = DbLogHandler.getInstance();
		try{			
			int hours = Integer.parseInt(getValue(request.getPart("hours")));
			int minutes = Integer.parseInt(getValue(request.getPart("minutes")));
			int seconds = Integer.parseInt(getValue(request.getPart("seconds")));
			int milliseconds = Integer.parseInt(getValue(request.getPart("milliseconds")));
			Part originalFile = request.getPart("file");			
		    String fileName = getFileName(originalFile);		
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(originalFile.getInputStream(), Charset.forName("ISO-8859-15")));
			SrtSynchronizer synchonizer = new SrtSynchronizer(new Variation(hours, minutes, seconds, milliseconds));
			String line = null;
			while( (line = fileReader.readLine()) != null){
				synchonizer.synch(line);
			}		
			fileReader.close();
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition","attachment;filename=" + "synched-"+ fileName);
			synchonizer.write(response.getWriter());
			log.write(fileName, request.getRemoteAddr(), 1);
		}catch(Exception e){
			System.err.println("Error found while processing SRT file");
			request.setAttribute("message", "Error while processing SRT file, please try again");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			log.write("", request.getRemoteAddr(), 0);
		}
	}
}
