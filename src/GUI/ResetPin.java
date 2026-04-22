package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.AccNotFound;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import Data.FileIO;
import java.awt.SystemColor;

public class ResetPin extends JFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldAccountNumber;
	private JTextField textFieldNewPin;


	/**
	 * Create the frame.
	 */
	public ResetPin() {
		setTitle("Reset PIN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResetPin = new JLabel("Reset PIN");
		lblResetPin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblResetPin.setHorizontalAlignment(SwingConstants.CENTER);
		lblResetPin.setBounds(10, 11, 414, 36);
		contentPane.add(lblResetPin);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccountNumber.setBounds(0, 86, 106, 14);
		contentPane.add(lblAccountNumber);
		
		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setBounds(116, 83, 216, 20);
		contentPane.add(textFieldAccountNumber);
		textFieldAccountNumber.setColumns(10);
		
		textFieldNewPin = new JTextField();
		textFieldNewPin.setColumns(10);
		textFieldNewPin.setBounds(116, 147, 216, 20);
		contentPane.add(textFieldNewPin);
		
		JLabel lblNewPin = new JLabel("New PIN:");
		lblNewPin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPin.setBounds(10, 150, 96, 14);
		contentPane.add(lblNewPin);
		
		JButton btnResetPin = new JButton("Reset PIN");
		btnResetPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String accountNum;
				int newPin;
				accountNum = textFieldAccountNumber.getText();
				
				try {
					newPin = Integer.parseInt(textFieldNewPin.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(getComponent(0), "PIN must be a valid integer");
					textFieldNewPin.setText(null);
					return;
				}
				
				try {
					int confirm = JOptionPane.showConfirmDialog(getComponent(0), "Confirm PIN reset to: " + newPin + "?");
					if(confirm == 0)
					{
						Bank.BankAccount account = FileIO.bank.findAccount(accountNum);
						if(account == null) {
							throw new AccNotFound("Account Not Found");
						}
						
						account.resetPin(newPin);
						JOptionPane.showMessageDialog(getComponent(0),"PIN Reset Successful");
						textFieldAccountNumber.setText(null);
						textFieldNewPin.setText(null);
						dispose();
					}
					else
					{
						textFieldAccountNumber.setText(null);
						textFieldNewPin.setText(null);
					}
					
				} catch (AccNotFound e1) {
					JOptionPane.showMessageDialog(getComponent(0), "Sorry! Account Not Found");
					JOptionPane.showMessageDialog(getComponent(0),"Failed");
					textFieldAccountNumber.setText(null);
					textFieldNewPin.setText(null);
				}
			}
		});
		btnResetPin.setBounds(145, 213, 137, 33);
		contentPane.add(btnResetPin);
	}
}
