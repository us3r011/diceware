import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
public class GUIFrame extends JFrame{
	private JTextField textField;
	private JTextArea textArea;
	private JButton button;
	private StringBuilder passphrase;
			
			
	
	public GUIFrame() {
		add(createPanel());
		setSize(500, 150);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Number of words: ");
		textField = new JTextField(10);
		JPanel firstPanel = new JPanel();
		firstPanel.add(label);
		firstPanel.add(textField);
		
		panel.add(firstPanel, BorderLayout.NORTH);
		
		
		textArea = new JTextArea();
		panel.add(textArea, BorderLayout.CENTER);
		
		button = new JButton("Generate Passphrase");
		button.setSize(20, 10);
		panel.add(button, BorderLayout.SOUTH);
		ActionListener listener = new buttonListener();
		button.addActionListener(listener);
		
		return panel;
		
	}
	
	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			passphrase = new StringBuilder();
			for(Integer i = 0; i < Integer.parseInt(textField.getText()); i++) {
				try {
					passphrase.append(" " + generateWord());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			String pass = passphrase.toString();
			textArea.setText(pass);
			
		}
	}
	
	
	public String generateWord() throws FileNotFoundException {
		StringBuilder number = new StringBuilder();
		for(int i = 0; i<5; i++) {
			
			number.append(lancia());
			
			
		}
		String strNumber = number.toString();
		String parola = findWord(strNumber);

		return parola;
	}
	
	public String findWord(String numero) throws FileNotFoundException {
		File f = new File("diceList.txt");
		String word = new String();
		Scanner scan = new Scanner(f);
		
		String line;
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains(numero)) {
				String parentStringValue = line.substring(line.indexOf(" ")+7);
				word = parentStringValue;
				
			}
			line = null;
		}
		
		return word;
		
	}
	
	public int lancia() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
}
