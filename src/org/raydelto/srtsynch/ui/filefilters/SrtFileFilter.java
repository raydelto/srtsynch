/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.ui.filefilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SrtFileFilter extends FileFilter{
	private static SrtFileFilter instance;
	
	public static synchronized SrtFileFilter getInstance(){
		return instance == null ? instance = new SrtFileFilter() : instance;
	}
	private  SrtFileFilter() {
	}

	@Override
	public boolean accept(File f) {				
		String fileName = f.getAbsolutePath();
		return fileName.substring(fileName.length()-4, fileName.length()).equalsIgnoreCase(".srt");
	}

	@Override
	public String getDescription() {
		return "SRT Subtitles format (*.srt)";
	}
}
