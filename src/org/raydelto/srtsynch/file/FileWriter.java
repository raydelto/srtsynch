/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.file;

import java.io.IOException;
import java.io.PrintWriter;


public class FileWriter {
	public void write(String fileName, String text) throws IOException{
		PrintWriter writer = new PrintWriter(new java.io.FileWriter(fileName));
		writer.print(text);
		writer.close();
	}
}
