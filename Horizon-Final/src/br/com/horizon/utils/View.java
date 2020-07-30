package br.com.horizon.utils;


import javax.swing.JOptionPane;

public class View {
	
	//Input
	
		//Integer
	public static int inputInt(String title, String message) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE));	
	}
	
		//Float
	public static float inputFloat(String title, String message) {
		return Float.parseFloat(JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE));
	}
	
		//String
	public static String inputString(String title, String message) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	//Print
	
		//Text
	public static void showText(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
		//Warning
	public static void showWarning(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
}	
