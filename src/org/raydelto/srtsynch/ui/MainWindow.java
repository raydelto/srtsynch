/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.raydelto.srtsynch.srt.SrtSynchronizer;
import org.raydelto.srtsynch.srt.Variation;
import org.raydelto.srtsynch.ui.filefilters.SrtFileFilter;
import org.raydelto.srtsynch.ui.listeners.NumericListener;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public MainWindow(){
		super("SRT Synch v0.1 . http://www.raydelto.org");
		setResizable(false);
		setLayout(new GridLayout(5, 1));
		//Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.add(new JLabel("Enter the amount of hours, minutes and seconds that you want to adjust (add or substract, depending if you type positive or negative numbers)"));
		add(pnlTitle);
		
		//Settings 
		JPanel pnlSettings = new JPanel();
		pnlSettings.setLayout(new GridLayout(1,8));
		pnlSettings.add(new JLabel("Hours: "));
		
		final JTextField txtHours = new JTextField(2);		
		NumericListener numeric = new NumericListener();
		txtHours.addKeyListener(numeric);		
		final JTextField txtMinutes	= new JTextField(2);
		txtMinutes.addKeyListener(numeric);
		final JTextField txtSeconds = new JTextField(2);
		txtSeconds.addKeyListener(numeric);		
		final JTextField txtMilliseconds = new JTextField(2);
		txtMilliseconds.addKeyListener(numeric);		
		
		pnlSettings.add(txtHours);
		pnlSettings.add(new JLabel("Minutes: "));
		pnlSettings.add(txtMinutes);
		pnlSettings.add(new JLabel("Seconds: "));
		pnlSettings.add(txtSeconds);
		pnlSettings.add(new JLabel("Milliseconds: "));
		pnlSettings.add(txtMilliseconds);

		
		add(pnlSettings);
		
		//Original SRT File
		JPanel pnlOriginal = new JPanel();
		pnlOriginal.setLayout(new GridLayout(1, 3));
		pnlOriginal.add(new JLabel("Original File Location"));
		final JTextField txtOriginal = new JTextField(20);
		txtOriginal.setEditable(false);
		pnlOriginal.add(txtOriginal);
		JButton btnBrowseOriginal = new JButton("Browse ...");
		pnlOriginal.add(btnBrowseOriginal);
		btnBrowseOriginal.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(SrtFileFilter.getInstance());				
				chooser.showOpenDialog(MainWindow.this);				
				if(chooser.getSelectedFile() != null){
					txtOriginal.setText(chooser.getSelectedFile().getAbsolutePath());
				}
				
			}
		});
		add(pnlOriginal);
		//Destination Synched SRT File
		JPanel pnlDestination = new JPanel();
		pnlDestination.setLayout(new GridLayout(1, 3));
		pnlDestination.add(new JLabel("Destination File Location"));
		final JTextField txtDestination = new JTextField(20);
		txtDestination.setEditable(false);
		pnlDestination.add(txtDestination);
		JButton btnBrowseDestination = new JButton("Browse ...");
		pnlDestination.add(btnBrowseDestination);
		btnBrowseDestination.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(SrtFileFilter.getInstance());
				chooser.showSaveDialog(MainWindow.this);
				if(chooser.getSelectedFile() != null){
					txtDestination.setText(chooser.getSelectedFile().getAbsolutePath());
				}
				
			}
		});
		add(pnlDestination);
		
		//Process Button		
		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtOriginal.getText().equals("")){
					JOptionPane.showMessageDialog(MainWindow.this,"You need to specify the original SRT File", "Specify Original SRT File", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtDestination.getText().equals("")){
					JOptionPane.showMessageDialog(MainWindow.this,"You need to specify the destination SRT File", "Specify Destination SRT File", JOptionPane.WARNING_MESSAGE);
					return;
				}

				
				if(txtHours.getText().equals("")){
					txtHours.setText("0");
				}
				if(txtMinutes.getText().equals("")){
					txtMinutes.setText("0");
				}

				if(txtSeconds.getText().equals("")){
					txtSeconds.setText("0");
				}

				SrtSynchronizer synch = new SrtSynchronizer(new Variation(Integer.parseInt(txtHours.getText()), Integer.parseInt(txtMinutes.getText()), Integer.parseInt(txtSeconds.getText()), Integer.parseInt(txtMilliseconds.getText())), txtOriginal.getText(), txtDestination.getText());
				try {
					synch.start();
					JOptionPane.showMessageDialog(MainWindow.this,"File " + txtOriginal.getText() + " has been successfully Synched", "File synched", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MainWindow.this,"Error while synchronizing message: " + e1,"Error while converting", JOptionPane.ERROR_MESSAGE);
					
				}
			
			}
		});
		add(btnProcess);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
