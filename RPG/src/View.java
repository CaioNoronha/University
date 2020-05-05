import javax.swing.JOptionPane;

public class View {
	
	
	//Input data
	
		//Integer
	public static int inputInt(String title, String message) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE));	
	}
	
		//String
	public static String inputString(String title, String message) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
	//Show messages
	
		//String message
	public static void showStringMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
		//Error message
	public static void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

}
