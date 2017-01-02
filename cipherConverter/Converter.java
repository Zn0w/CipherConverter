package cipherConverter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Converter extends JFrame {

	private JPanel contentPane;
	
	private JTextArea sourceText;
	private JTextArea transformedText;
	private JButton btnCopy;

	public Converter() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sourceText = new JTextArea();
		sourceText.setText("ATTENTION!\n" + "Please, don't use # when you typing an unciphered text.\n" + "Insert here text that you want to be encrypted or decrypted.");
		sourceText.setBounds(10, 11, 594, 315);
		contentPane.add(sourceText);
		
		transformedText = new JTextArea();
		transformedText.setEditable(false);
		transformedText.setBounds(10, 400, 594, 351);
		contentPane.add(transformedText);
		
		JButton btnTransform = new JButton("Encrypt/Decrypt");
		btnTransform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check(sourceText.getText()))
					transformedText.setText(encryptText(sourceText.getText()));
				else
					transformedText.setText(decryptText(sourceText.getText()));
			}
		});
		btnTransform.setBounds(242, 337, 130, 23);
		contentPane.add(btnTransform);
		setLocationRelativeTo(null);
		
		// Clear the "transformed" text area
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transformedText.setText("");
			}
		});
		btnClear.setBounds(232, 366, 70, 23);
		contentPane.add(btnClear);
		
		// Copy text from the "transformed" text area to the clipboard
		btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(transformedText.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopy.setBounds(322, 366, 70, 23);
		contentPane.add(btnCopy);
		setVisible(true);
	}
	
	// This function checks if the input text is encrypted or not (since the program change empty space to #, the function is looking for the #)
	public boolean check(String srcTxt) {
		for (int i = 0; i < srcTxt.length(); i++) {
			if (srcTxt.charAt(i) == '#')
				return false;
		}
		
		return true;
	}
	
	// The function moves letters alphabetically to the right
	public String encryptText(String srcTxt) {
		String cipherTxt = "";
		
		for (int i = 0; i < srcTxt.length(); i++) {
			cipherTxt += (char) (srcTxt.charAt(i) + 3);
		}
		
		return cipherTxt;
	}
	
	// Decryption works like encrypt function but moves letter to the left
	public String decryptText(String srcTxt) {
		String normalTxt = "";
		
		for (int i = 0; i < srcTxt.length(); i++) {
			if (srcTxt.charAt(i) == '\n')
				normalTxt += "\n";
			
			normalTxt += (char) (srcTxt.charAt(i) - 3);
		}
		
		return normalTxt;
	}
}
