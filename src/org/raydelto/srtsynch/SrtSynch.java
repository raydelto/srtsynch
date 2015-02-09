/**
 * @author Raydelto
 */
package org.raydelto.srtsynch;

import javax.swing.UIManager;
import org.raydelto.srtsynch.ui.MainWindow;

public class SrtSynch {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Cannot adopt host Operating System Look and feel: " + e);
		}		new MainWindow();
	}
}
