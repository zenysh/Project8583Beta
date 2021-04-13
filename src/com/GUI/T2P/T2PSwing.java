package com.GUI.T2P;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.GUI.ConvertHex.HexToBinary;
import com.GUI.FieldsModifiers.FieldModifier;
import com.GUI.Password.EncryptAndDecryptAndReturn;
import com.GUI.ThreadSender.BulkRequestor;
import com.GUI.ThreadSender.BulkSenderOwnListner;
import com.GUI.ThreadSender.BulkSenderThread;
import com.GUI.ThreadSender.ISOSender;
import com.GUI.date.TranDate;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class T2PSwing {

	private JFrame frame;
	private boolean passwordmatch = false;
	private Socket socket;
	private JTextField textField_ProcessCode;
	private JTextField textField_messageCode;
	private JTextField textField_btMap1;
	private JTextField textField_btMap2;
	private JTextField textField_hexValues1;
	private JTextField textField_hexValues2;
	private JTextField textField_from;
	private JTextField textField_To;
	private JTextField textField_terminal;
	private JTextField textField_Amount;
	private JTextField textField_primary;
	private JTextField textField_secondarybtmap;
	private JTextField textField_PAN;
	private JTextField textField_amount;
	private JTextField textField_Date;
	private JTextField textField;
	private JTextField textField_1_time;
	private JTextField textField_DateLocal;
	private JTextField textField_DateExp;
	private JTextField textField_1;
	private JTextField textField_Merchant;
	private JTextField textField_Service;
	private JTextField textField_condition;
	private JTextField textField_Acquirer;
	private JTextField textField_track;
	private JTextField textField_retrival;
	private JTextField textField_Terminal;
	private JTextField textField_cardidentity;
	private JTextField textField_cardlocation;
	private JTextField textField_field46;
	private JTextField textField_Currency;
	private JTextField textField_PIN;
	private JTextField textField_Ac1;
	private JTextField textField_Ac2;
	private JTextField textField_123;
	private JTextField textField_124;
	private JTextField textField_Server;
	private JTextField textField_Port;
	private JTextField textField_responseCode;
	private JTextField textField_Acc1;
	private JTextField textField_priBit_res;
	private JTextField textField_bt_Ex_res;
	private JTextField textField_PANRes;
	private JTextField textField_AmtRes;
	private JTextField textField_TranDate_res;
	private JTextField textField_SysTrace_res;
	private JTextField textField_TimeLocal_res;
	private JTextField textField_LocalTranRes;
	private JTextField textField_Datesettleres;
	private JTextField textField_merchan_res;
	private JTextField textField_Acquirer_res;
	private JTextField textField_auth_res;
	private JTextField textField_Teminal_res;
	private JTextField textField_adDataN;
	private JTextField textField_AdDataP;
	private JTextField textField_Curr_res;
	private JTextField textField_Acc2;
	private JTextField textField_AddAm_res;
	private boolean bulkfundtransfer = false;
	private boolean bulkbalanceenq = false;
	private boolean bulkministatement = false;
	JRadioButton rbBI;
	JRadioButton rbFT;
	JRadioButton rbMS;
	int inputs = 0;
	/*--------------------------------------------*/
	HexToBinary hex = new HexToBinary();
	private JTextField textField_proCode;
	private JTextField textField_amt_settle;
	JEditorPane editorPane = new JEditorPane();
	private JTextField textField_OriData;
	JPanel panel_1 = new JPanel();
	JLabel lblNewLabel_48;
	private JTextField textField_authorization;
	private JTextField textField_responseCODE;
	private JTextField textField_retRes;
	private JPasswordField textField_2;
	private int triesleft = 4;
	private JTextField textFieldBulknumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T2PSwing window = new T2PSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public T2PSwing() {
		initialize();

	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean isInteger(Object object) {
		if (object instanceof Integer) {
			return true;
		} else {
			String string = object.toString();

			try {
				Integer.parseInt(string);
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 842);
		// frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE));
		tabbedPane.setPreferredSize(new Dimension(700, 850));
		JScrollPane scrollFrame = new JScrollPane(tabbedPane);
		tabbedPane.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(200, 300));
		frame.getContentPane().add(scrollFrame);

		Panel panel = new Panel();
		tabbedPane.addTab("Parameters", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Financial Transaction");
		lblNewLabel.setBounds(10, 11, 141, 22);
		panel.add(lblNewLabel);

		JLabel lblTransactionType = new JLabel("Transaction Type :");
		lblTransactionType.setBounds(10, 44, 117, 22);
		panel.add(lblTransactionType);

		textField_btMap1 = new JTextField();
		textField_btMap1.setBounds(180, 94, 468, 20);
		panel.add(textField_btMap1);
		textField_btMap1.setText("1111001000111110010001001000000100101000111001001001000000000000");
		textField_btMap1.setColumns(10);

		textField_btMap2 = new JTextField();
		textField_btMap2.setColumns(10);
		textField_btMap2.setBounds(180, 119, 468, 20);
		textField_btMap2.setText("0000000000000000000000000000000000000110000000000000000000110000");
		panel.add(textField_btMap2);

		Choice choice = new Choice();
		choice.setBounds(180, 44, 113, 20);
		choice.add("Fund Transfer");
		choice.add("Balance Enquiry");
		choice.add("Mini-Statement");
		choice.add("Purchase");
		choice.add("Reversal");
		panel.add(choice);
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (choice.getSelectedItem().equalsIgnoreCase("Fund Transfer")) {
					textField_ProcessCode.setText("400000");
					textField_messageCode.setText("0200");
					textField_hexValues1.setText("F23E448128E49000");
					textField_hexValues2.setText("0000000006000030");
					textField_btMap1.setText(hex.ConverthexToBinary(textField_hexValues1.getText()));
					textField_btMap2.setText(hex.ConverthexToBinary(textField_hexValues2.getText()));
				} else if (choice.getSelectedItem().equalsIgnoreCase("Balance Enquiry")) {
					textField_ProcessCode.setText("301000");
					textField_messageCode.setText("0200");
					textField_hexValues1.setText("F23E448128E49000");
					textField_hexValues2.setText("0000000006000030");
					textField_btMap1.setText(hex.ConverthexToBinary(textField_hexValues1.getText()));
					textField_btMap2.setText(hex.ConverthexToBinary(textField_hexValues2.getText()));
				} else if (choice.getSelectedItem().equalsIgnoreCase("Mini-Statement")) {
					textField_ProcessCode.setText("350000");
					textField_messageCode.setText("0200");
					textField_hexValues1.setText("F23E448128E49000");
					textField_hexValues2.setText("0000000006000030");
					textField_btMap1.setText(hex.ConverthexToBinary(textField_hexValues1.getText()));
					textField_btMap2.setText(hex.ConverthexToBinary(textField_hexValues2.getText()));
				} else if (choice.getSelectedItem().equalsIgnoreCase("Purchase")) {
					textField_ProcessCode.setText("001000");
					textField_messageCode.setText("0200");
					textField_hexValues1.setText("F23E448128E49000");
					textField_hexValues2.setText("0000000006000030");
					textField_btMap1.setText(hex.ConverthexToBinary(textField_hexValues1.getText()));
					textField_btMap2.setText(hex.ConverthexToBinary(textField_hexValues2.getText()));
				} else if (choice.getSelectedItem().equalsIgnoreCase("Reversal")) {
					textField_ProcessCode.setText("400000");
					textField_messageCode.setText("0420");
					textField_hexValues1.setText("F23A44810EE48000");
					textField_hexValues2.setText("0000004006000000");
					textField_btMap1.setText(hex.ConverthexToBinary(textField_hexValues1.getText()));
					textField_btMap2.setText(hex.ConverthexToBinary(textField_hexValues2.getText()));

				}
			}
		});
		JLabel lblProcessCode = new JLabel("Process Code:");
		lblProcessCode.setBounds(10, 72, 86, 14);
		panel.add(lblProcessCode);

		textField_ProcessCode = new JTextField();
		textField_ProcessCode.setBounds(180, 69, 113, 20);
		textField_ProcessCode.setText("400000");
		panel.add(textField_ProcessCode);
		textField_ProcessCode.setEditable(false);
		textField_ProcessCode.setColumns(10);

		JLabel lblMessageCode = new JLabel("Message Code");
		lblMessageCode.setBounds(302, 46, 86, 18);
		panel.add(lblMessageCode);

		textField_messageCode = new JTextField();
		textField_messageCode.setBounds(294, 69, 86, 20);
		textField_messageCode.setText("0200");
		panel.add(textField_messageCode);
		textField_messageCode.setEditable(false);
		textField_messageCode.setColumns(10);

		JLabel lblBitmapstr = new JLabel("BitHPMapStr1 :");
		lblBitmapstr.setBounds(10, 97, 92, 14);
		panel.add(lblBitmapstr);

		JLabel lblBithpmapstr = new JLabel("BitHPMapStr2 :");
		lblBithpmapstr.setBounds(10, 122, 92, 14);
		panel.add(lblBithpmapstr);

		textField_hexValues1 = new JTextField();
		textField_hexValues1.setBounds(180, 147, 144, 20);
		textField_hexValues1.setText("F23E448128E49000");
		panel.add(textField_hexValues1);
		textField_hexValues1.setColumns(10);

		textField_hexValues2 = new JTextField();
		textField_hexValues2.setColumns(10);
		textField_hexValues2.setBounds(334, 147, 146, 20);
		textField_hexValues2.setText("0000000006000030");
		panel.add(textField_hexValues2);

		JLabel lblHexValues = new JLabel("Hex Values");
		lblHexValues.setBounds(10, 147, 70, 14);
		panel.add(lblHexValues);

		JLabel lblFromAccount = new JLabel("Source MainCode :");
		lblFromAccount.setBounds(10, 172, 117, 22);
		panel.add(lblFromAccount);

		JLabel lblToAccount = new JLabel("Destination MainCode: ");
		lblToAccount.setBounds(10, 194, 141, 22);
		panel.add(lblToAccount);

		textField_from = new JTextField();
		textField_from.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_from.getText().length() >= 28) {
					e.consume();
				}
			}
		});
		textField_from.setBounds(180, 173, 199, 20);
		panel.add(textField_from);
		textField_from.setColumns(10);

		textField_To = new JTextField();
		textField_To.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_To.getText().length() >= 28) {
					e.consume();
				}
			}
		});
		textField_To.setColumns(10);
		textField_To.setBounds(180, 195, 199, 20);
		panel.add(textField_To);

		JLabel lblTerminalId = new JLabel("Terminal ID: ");
		lblTerminalId.setBounds(10, 224, 86, 14);
		panel.add(lblTerminalId);

		textField_terminal = new JTextField();
		textField_terminal.setBounds(180, 221, 102, 20);
		panel.add(textField_terminal);
		textField_terminal.setColumns(10);

		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(10, 249, 62, 20);
		panel.add(lblAmount);

		textField_Amount = new JTextField();
		textField_Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Amount.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		textField_Amount.setBounds(180, 252, 102, 20);
		panel.add(textField_Amount);
		textField_Amount.setColumns(10);

		Button button = new Button("Next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				TranDate d = new TranDate();
				Random rnd = new Random();
				textField_Ac1.setText(textField_from.getText());
				textField_amount.setText(textField_Amount.getText());
				textField_Ac2.setText(textField_To.getText());
				textField_Terminal.setText(textField_terminal.getText());
				textField_proCode.setText(textField_ProcessCode.getText());
				textField_primary.setText(textField_hexValues1.getText());
				textField_secondarybtmap.setText(textField_hexValues2.getText());
				textField_Date.setText(d.getTranDateTime());
				textField.setText(String.valueOf(100000 + rnd.nextInt(900000)));
				textField_1_time.setText(d.getTimeLocal());
				textField_DateLocal.setText(d.getLocalSettle());
				textField_DateExp.setText(d.getexpDate());
				textField_1.setText(d.getLocalSettle());
				textField_retrival.setText(String.valueOf(d.generateRandom(12)));

				if (textField_messageCode.getText().equals("0420")) {
					// reversalfield();
//					textField_primary.setText("F23A44810EE48000");
//					textField_secondarybtmap.setText("0000004006000000");
					textField_OriData.setEnabled(true);
					textField_DateExp.setEnabled(false);
					textField_DateExp.setEnabled(false);
					textField_track.setEnabled(false);
					textField_PIN.setEnabled(false);
					textField_123.setEnabled(false);
					textField_124.setEnabled(false);
					textField_authorization.setEnabled(true);
					textField_responseCODE.setEnabled(true);
				} else {
					textField_authorization.setEnabled(false);
					textField_responseCODE.setEnabled(false);
					textField_OriData.setEnabled(false);
					textField_DateExp.setEnabled(true);
					textField_DateExp.setEnabled(true);
					textField_track.setEnabled(true);
					textField_PIN.setEnabled(true);
					textField_123.setEnabled(true);
					textField_124.setEnabled(true);
				}
			}
		});
		button.setBounds(10, 283, 70, 22);
		button.setEnabled(false);
		panel.add(button);

		JLabel lblNewLabel_52 = new JLabel("Password : ");
		lblNewLabel_52.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_52.setBounds(180, 12, 81, 18);
		panel.add(lblNewLabel_52);

		textField_2 = new JPasswordField();
		textField_2.setBounds(264, 12, 163, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_53 = new JLabel("Locked");
		lblNewLabel_53.setForeground(Color.RED);
		lblNewLabel_53.setBounds(571, 15, 54, 14);
		panel.add(lblNewLabel_53);
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField_2.requestFocus();
			}
		});

		JLabel lblNewLabel_54 = new JLabel("Status : ");
		lblNewLabel_54.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblNewLabel_54.setBounds(523, 15, 54, 14);
		panel.add(lblNewLabel_54);

		tabbedPane.addTab("Values", null, panel_1, null);

		JLabel lblNewLabel_1 = new JLabel("0. Primary BitMap: ");
		lblNewLabel_1.setBounds(20, 30, 126, 17);

		textField_primary = new JTextField();
		textField_primary.setBounds(178, 28, 140, 20);
		textField_primary.setColumns(10);

		JLabel lblSecondayBitMap = new JLabel("1. Seconday Bit Map: ");
		lblSecondayBitMap.setBounds(20, 51, 126, 20);

		textField_secondarybtmap = new JTextField();
		textField_secondarybtmap.setBounds(178, 51, 140, 20);
		textField_secondarybtmap.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("2. Primry Account Number:");
		lblNewLabel_2.setBounds(20, 79, 160, 14);

		textField_PAN = new JTextField();
		textField_PAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_PAN.getText().length() >= 19) {
					e.consume();
				}
			}
		});
		textField_PAN.setBounds(178, 76, 140, 20);
		textField_PAN.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("4. Amount :");
		lblNewLabel_3.setBounds(20, 104, 91, 14);

		textField_amount = new JTextField();
		textField_amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_amount.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		textField_amount.setBounds(178, 101, 140, 20);
		textField_amount.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("7. Transmission Date :");
		lblNewLabel_4.setBounds(20, 129, 148, 14);

		textField_Date = new JTextField();
		textField_Date.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Date.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		textField_Date.setBounds(178, 127, 140, 23);
		textField_Date.setText("");
		textField_Date.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("11. System Trace :");
		lblNewLabel_5.setBounds(20, 154, 114, 14);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField.getText().length() >= 6) {
					e.consume();
				}
			}
		});
		textField.setBounds(178, 151, 140, 20);
		textField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("12. Time, Local : ");
		lblNewLabel_6.setBounds(20, 179, 114, 14);

		textField_1_time = new JTextField();
		textField_1_time.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_1_time.getText().length() >= 6) {
					e.consume();
				}
			}
		});
		textField_1_time.setBounds(178, 176, 104, 20);
		textField_1_time.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("13. Date, Local :");
		lblNewLabel_7.setBounds(20, 204, 91, 14);

		textField_DateLocal = new JTextField();
		textField_DateLocal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_DateLocal.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		textField_DateLocal.setBounds(178, 201, 68, 20);
		textField_DateLocal.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("14. Date, Exp");
		lblNewLabel_8.setBounds(20, 229, 91, 14);

		textField_DateExp = new JTextField();
		textField_DateExp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_DateExp.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		textField_DateExp.setBounds(178, 226, 68, 20);
		textField_DateExp.setColumns(10);

		lblNewLabel_48 = new JLabel("90. Original Data Element :");
		lblNewLabel_48.setBounds(328, 54, 168, 14);
		panel_1.add(lblNewLabel_48);

		textField_OriData = new JTextField();
		textField_OriData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_OriData.getText().length() >= 42) {
					e.consume();
				}
			}
		});
		textField_OriData.setColumns(10);
		textField_OriData.setBounds(328, 76, 350, 20);
		textField_OriData.setEnabled(false);
		panel_1.add(textField_OriData);

		JLabel lblNewLabel_9 = new JLabel("15. Date Settle :");
		lblNewLabel_9.setBounds(20, 254, 91, 14);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_1.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		textField_1.setBounds(178, 248, 68, 20);
		textField_1.setColumns(10);
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel_1);
		panel_1.add(textField_primary);
		panel_1.add(lblSecondayBitMap);
		panel_1.add(textField_secondarybtmap);
		panel_1.add(lblNewLabel_2);
		panel_1.add(textField_PAN);
		panel_1.add(lblNewLabel_3);
		panel_1.add(textField_amount);
		panel_1.add(lblNewLabel_4);
		panel_1.add(textField_Date);
		panel_1.add(lblNewLabel_5);
		panel_1.add(textField);
		panel_1.add(lblNewLabel_6);
		panel_1.add(textField_1_time);
		panel_1.add(lblNewLabel_7);
		panel_1.add(textField_DateLocal);
		panel_1.add(lblNewLabel_8);
		panel_1.add(textField_DateExp);
		panel_1.add(lblNewLabel_9);
		panel_1.add(textField_1);

		JLabel lblNewLabel_10 = new JLabel("18. Merchant Type : ");
		lblNewLabel_10.setBounds(20, 279, 126, 14);
		panel_1.add(lblNewLabel_10);

		textField_Merchant = new JTextField();
		textField_Merchant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Merchant.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		textField_Merchant.setBounds(178, 276, 41, 20);
		panel_1.add(textField_Merchant);
		textField_Merchant.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("22. Point of service :");
		lblNewLabel_11.setBounds(20, 307, 134, 14);
		panel_1.add(lblNewLabel_11);

		textField_Service = new JTextField();
		textField_Service.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Service.getText().length() >= 3) {
					e.consume();
				}
			}
		});
		textField_Service.setBounds(178, 304, 30, 20);
		panel_1.add(textField_Service);
		textField_Service.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("25. Point of condition : ");
		lblNewLabel_12.setBounds(20, 332, 148, 14);
		panel_1.add(lblNewLabel_12);

		textField_condition = new JTextField();
		textField_condition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_condition.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		textField_condition.setBounds(178, 329, 19, 20);
		panel_1.add(textField_condition);
		textField_condition.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("32. Acquirer Id :");
		lblNewLabel_13.setBounds(20, 357, 126, 14);
		panel_1.add(lblNewLabel_13);

		textField_Acquirer = new JTextField();
		textField_Acquirer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Acquirer.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		textField_Acquirer.setBounds(178, 354, 140, 20);
		panel_1.add(textField_Acquirer);
		textField_Acquirer.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("35. Track 2 data : ");
		lblNewLabel_14.setBounds(20, 382, 126, 14);
		panel_1.add(lblNewLabel_14);

		textField_track = new JTextField();
		textField_track.setBounds(178, 379, 140, 20);
		panel_1.add(textField_track);
		textField_track.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("37. Retrieval ref : ");
		lblNewLabel_15.setBounds(20, 407, 104, 14);
		panel_1.add(lblNewLabel_15);

		textField_retrival = new JTextField();
		textField_retrival.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_retrival.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		textField_retrival.setBounds(177, 404, 91, 20);
		panel_1.add(textField_retrival);
		textField_retrival.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("41. Terminal Id :");
		lblNewLabel_16.setBounds(20, 431, 126, 14);
		panel_1.add(lblNewLabel_16);

		textField_Terminal = new JTextField();
		textField_Terminal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Terminal.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		textField_Terminal.setBounds(178, 428, 140, 20);
		panel_1.add(textField_Terminal);
		textField_Terminal.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("42. Card Identification : ");
		lblNewLabel_17.setBounds(20, 456, 134, 14);
		panel_1.add(lblNewLabel_17);

		textField_cardidentity = new JTextField();
		textField_cardidentity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_cardidentity.getText().length() >= 15) {
					e.consume();
				}
			}
		});
		textField_cardidentity.setBounds(178, 453, 140, 20);
		panel_1.add(textField_cardidentity);
		textField_cardidentity.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("43. Card Location :");
		lblNewLabel_18.setBounds(20, 478, 114, 14);
		panel_1.add(lblNewLabel_18);

		textField_cardlocation = new JTextField();
		textField_cardlocation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_cardlocation.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		textField_cardlocation.setBounds(178, 475, 140, 20);
		panel_1.add(textField_cardlocation);
		textField_cardlocation.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("46. Additional data iso : ");
		lblNewLabel_19.setBounds(20, 499, 148, 14);
		panel_1.add(lblNewLabel_19);

		textField_field46 = new JTextField();
		textField_field46.setBounds(178, 496, 318, 20);
		panel_1.add(textField_field46);
		textField_field46.setColumns(10);

		JLabel lblNewLabel_20 = new JLabel("49. Currency Code : ");
		lblNewLabel_20.setBounds(20, 524, 126, 14);
		panel_1.add(lblNewLabel_20);

		textField_Currency = new JTextField();
		textField_Currency.setBounds(178, 521, 30, 20);
		textField_Currency.setText("524");
		textField_Currency.setEditable(false);
		panel_1.add(textField_Currency);
		textField_Currency.setColumns(10);

		JLabel lblNewLabel_21 = new JLabel("52. PIN :");
		lblNewLabel_21.setBounds(20, 549, 126, 14);
		panel_1.add(lblNewLabel_21);

		JLabel lblNewLabel_22 = new JLabel("102. Account 1 :");
		lblNewLabel_22.setBounds(20, 574, 126, 14);
		panel_1.add(lblNewLabel_22);

		JLabel lblAccount = new JLabel("103. Account 2 :");
		lblAccount.setBounds(20, 599, 126, 14);
		panel_1.add(lblAccount);

		JLabel lblReservedPrivateUse = new JLabel("123. Reserved private : 123");
		lblReservedPrivateUse.setBounds(20, 624, 160, 14);
		panel_1.add(lblReservedPrivateUse);

		JLabel lblReservedPrivateUse_1 = new JLabel("124. Reserved private : 124");
		lblReservedPrivateUse_1.setBounds(20, 651, 160, 14);
		panel_1.add(lblReservedPrivateUse_1);

		textField_PIN = new JTextField();
		textField_PIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_PIN.getText().length() >= 16) {
					e.consume();
				}
			}
		});
		textField_PIN.setBounds(178, 546, 140, 20);
		panel_1.add(textField_PIN);
		textField_PIN.setColumns(10);

		textField_Ac1 = new JTextField();
		textField_Ac1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Ac1.getText().length() >= 28) {
					e.consume();
				}
			}
		});
		textField_Ac1.setColumns(10);
		textField_Ac1.setBounds(178, 571, 140, 20);
		panel_1.add(textField_Ac1);

		textField_Ac2 = new JTextField();
		textField_Ac2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_Ac2.getText().length() >= 28) {
					e.consume();
				}
			}
		});
		textField_Ac2.setColumns(10);
		textField_Ac2.setBounds(178, 596, 140, 20);
		panel_1.add(textField_Ac2);

		textField_123 = new JTextField();
		textField_123.setColumns(10);
		textField_123.setBounds(188, 621, 140, 20);
		panel_1.add(textField_123);

		textField_124 = new JTextField();
		textField_124.setColumns(10);
		textField_124.setBounds(190, 648, 140, 20);
		panel_1.add(textField_124);
		// button placement

		JLabel lblNewLabel_46 = new JLabel("3. Pro Code : ");
		lblNewLabel_46.setBounds(333, 31, 84, 14);
		panel_1.add(lblNewLabel_46);

		textField_proCode = new JTextField();
		textField_proCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_proCode.getText().length() >= 6) {
					e.consume();
				}
			}
		});
		textField_proCode.setBounds(427, 28, 75, 20);
		panel_1.add(textField_proCode);
		textField_proCode.setColumns(10);

		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				scrollFrame.getViewport().setViewPosition(new Point(0, 0));
				frame.invalidate();
				frame.validate();
				frame.repaint();
				if (textField_messageCode.getText().equals("0420")) {
					editorPane.setText(MakeISO8583Reversal());
				} else {
					editorPane.setText(Makeiso8583());
				}
			}
		});

		btnNewButton.setBounds(20, 676, 91, 23);
		btnNewButton.setEnabled(false);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_49 = new JLabel("38. Authorization identity: ");
		lblNewLabel_49.setBounds(338, 101, 148, 17);
		panel_1.add(lblNewLabel_49);

		textField_authorization = new JTextField();
		textField_authorization.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_authorization.getText().length() >= 6) {
					e.consume();
				}
			}
		});
		textField_authorization.setBounds(496, 101, 91, 20);
		panel_1.add(textField_authorization);
		textField_authorization.setColumns(10);

		JLabel lblNewLabel_50 = new JLabel("39. Response Code :");
		lblNewLabel_50.setBounds(337, 129, 134, 14);
		panel_1.add(lblNewLabel_50);

		textField_responseCODE = new JTextField();
		textField_responseCODE.setColumns(10);
		textField_responseCODE.setBounds(454, 126, 54, 20);
		panel_1.add(textField_responseCODE);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Custom1", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_47 = new JLabel("Amount Settle: ");
		lblNewLabel_47.setBounds(10, 22, 91, 19);
		panel_3.add(lblNewLabel_47);

		textField_amt_settle = new JTextField();
		textField_amt_settle.setBounds(111, 21, 118, 20);
		panel_3.add(textField_amt_settle);
		textField_amt_settle.setColumns(10);
		/*
		 * Custom can be added For on and off fields. Dynamic Version is more effective
		 */
		// JPanel panel_4 = new JPanel();
		// tabbedPane.addTab("Custom2", null, panel_4, null);

		// JPanel panel_5 = new JPanel();
		// tabbedPane.addTab("Custom3", null, panel_5, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Connect", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_23 = new JLabel("Server  :");
		lblNewLabel_23.setBounds(10, 21, 52, 20);
		panel_2.add(lblNewLabel_23);

		JLabel lblPort = new JLabel("Port  :");
		lblPort.setBounds(195, 21, 43, 20);
		panel_2.add(lblPort);

		textField_Server = new JTextField();
		textField_Server.setBounds(69, 21, 116, 20);
		panel_2.add(textField_Server);
		textField_Server.setColumns(10);

		textField_Port = new JTextField();
		textField_Port.setBounds(233, 21, 58, 20);
		panel_2.add(textField_Port);
		textField_Port.setColumns(10);

		editorPane.setBounds(10, 75, 681, 90);
		panel_2.add(editorPane);

		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(10, 196, 681, 90);
		panel_2.add(editorPane_1);

		JLabel lblNewLabel_24 = new JLabel("Request :");
		lblNewLabel_24.setBounds(10, 52, 83, 12);
		panel_2.add(lblNewLabel_24);

		JLabel lblResponse = new JLabel("Response :");
		lblResponse.setBounds(10, 173, 73, 12);
		panel_2.add(lblResponse);

		JLabel lblNewLabel_25 = new JLabel("Response List");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_25.setBounds(10, 311, 104, 20);
		panel_2.add(lblNewLabel_25);

		JLabel lblNewLabel_26 = new JLabel("Response Code : ");
		lblNewLabel_26.setBounds(286, 345, 104, 14);
		panel_2.add(lblNewLabel_26);

		textField_responseCode = new JTextField();
		textField_responseCode.setBounds(412, 345, 43, 20);
		panel_2.add(textField_responseCode);
		textField_responseCode.setColumns(10);

		JLabel lblNewLabel_27 = new JLabel("Account identification 1 :");
		lblNewLabel_27.setBounds(273, 528, 148, 14);
		panel_2.add(lblNewLabel_27);

		textField_Acc1 = new JTextField();
		textField_Acc1.setBounds(431, 525, 203, 20);
		panel_2.add(textField_Acc1);
		textField_Acc1.setColumns(10);

		JLabel lblNewLabel_28 = new JLabel("Unsuccessful");
		lblNewLabel_28.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_28.setBounds(395, 21, 95, 19);
		panel_2.add(lblNewLabel_28);
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setBounds(115, 631, 536, 77);
		panel_2.add(editorPane_2);

		JLabel lblNewLabel_42 = new JLabel("Additional Data[N] : ");
		lblNewLabel_42.setBounds(274, 475, 147, 14);
		panel_2.add(lblNewLabel_42);

		JLabel lblAdditionalDatap = new JLabel("Additional Data[P] : ");
		lblAdditionalDatap.setBounds(274, 503, 116, 14);
		panel_2.add(lblAdditionalDatap);

		Button button_1 = new Button("Connect");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_Server.getText().isEmpty() || textField_Port.getText().isEmpty()) {
					T2PSwing.infoBox("Server/Port Can't be empty", "Fatal Error");
				} else if (!isInteger(textField_Port.getText())) {
					T2PSwing.infoBox("Port is not numeric", "Critical error");
				} else { /* Everything fine, continue */
					HashMap<String/* Field names */, JTextField/* textFields */> fieldmap = new HashMap<>();
					fieldmap.put("PrimaryBitmap", textField_priBit_res);
					fieldmap.put("BitmapExtended", textField_bt_Ex_res);
					fieldmap.put("Pan", textField_PANRes);
					fieldmap.put("Amount", textField_AmtRes);
					fieldmap.put("TranDate", textField_TranDate_res);
					fieldmap.put("SystemTrace", textField_SysTrace_res);
					fieldmap.put("TimeLocal", textField_TimeLocal_res);
					fieldmap.put("LocalTran", textField_LocalTranRes);
					fieldmap.put("DateSettle", textField_Datesettleres);
					fieldmap.put("AuthCode", textField_auth_res);
					fieldmap.put("AdditionalAm", textField_AddAm_res);
					fieldmap.put("ResponseCode", textField_responseCode);
					fieldmap.put("TerminalId", textField_Teminal_res);
					fieldmap.put("AcquirerID", textField_Acquirer_res);
					fieldmap.put("MerchantType", textField_merchan_res);
					fieldmap.put("RetrievalRef", textField_retRes);
					fieldmap.put("Currency", textField_Curr_res);
					fieldmap.put("AdditionalDataNational", textField_adDataN);
					fieldmap.put("AdditionalDataPriate", textField_AdDataP);
					fieldmap.put("Account1", textField_Acc1);
					fieldmap.put("Account2", textField_Acc2);
					new Thread(() -> {
						try {
							socket = new Socket(textField_Server.getText(), Integer.parseInt(textField_Port.getText()));
							ISOSender is = new ISOSender(socket, editorPane.getText(), editorPane_1, lblNewLabel_28,
									fieldmap, lblNewLabel_42, lblAdditionalDatap);
							is.Field48(editorPane_2, textField_ProcessCode, textField_messageCode);
							is.run();

							// ISOResponse ISR = new ISOResponse(socket,Makeiso8583());
							// ISR.run();
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							T2PSwing.infoBox("Connection Problem occured  " + e1.toString(), "Critical error");
							e1.printStackTrace();
						}
					}).start();

					// lblNewLabel_28.setText("Successful");
				}
			}
		});
		button_1.setBounds(303, 19, 73, 22);
		button_1.setEnabled(false);
		panel_2.add(button_1);

		JLabel lblNewLabel_29 = new JLabel("Primary BitMap :");
		lblNewLabel_29.setBounds(10, 342, 104, 20);
		panel_2.add(lblNewLabel_29);

		textField_priBit_res = new JTextField();
		textField_priBit_res.setBounds(115, 342, 148, 20);
		panel_2.add(textField_priBit_res);
		textField_priBit_res.setColumns(10);

		JLabel lblNewLabel_30 = new JLabel("Bit Map Ex :");
		lblNewLabel_30.setBounds(10, 373, 73, 14);
		panel_2.add(lblNewLabel_30);

		textField_bt_Ex_res = new JTextField();
		textField_bt_Ex_res.setBounds(115, 370, 148, 20);
		panel_2.add(textField_bt_Ex_res);
		textField_bt_Ex_res.setColumns(10);

		JLabel lblNewLabel_31 = new JLabel("PAN :");
		lblNewLabel_31.setBounds(10, 398, 52, 14);
		panel_2.add(lblNewLabel_31);

		textField_PANRes = new JTextField();
		textField_PANRes.setColumns(10);
		textField_PANRes.setBounds(115, 395, 148, 20);
		panel_2.add(textField_PANRes);

		JLabel lblNewLabel_32 = new JLabel("Amount :");
		lblNewLabel_32.setBounds(10, 423, 73, 14);
		panel_2.add(lblNewLabel_32);

		textField_AmtRes = new JTextField();
		textField_AmtRes.setColumns(10);
		textField_AmtRes.setBounds(115, 420, 148, 20);
		panel_2.add(textField_AmtRes);

		JLabel lblNewLabel_33 = new JLabel("Tran Date: ");
		lblNewLabel_33.setBounds(10, 448, 83, 14);
		panel_2.add(lblNewLabel_33);

		textField_TranDate_res = new JTextField();
		textField_TranDate_res.setColumns(10);
		textField_TranDate_res.setBounds(115, 447, 148, 20);
		panel_2.add(textField_TranDate_res);

		JLabel lblNewLabel_34 = new JLabel("System Trace :");
		lblNewLabel_34.setBounds(10, 478, 95, 14);
		panel_2.add(lblNewLabel_34);

		textField_SysTrace_res = new JTextField();
		textField_SysTrace_res.setColumns(10);
		textField_SysTrace_res.setBounds(115, 475, 148, 20);
		panel_2.add(textField_SysTrace_res);

		JLabel lblNewLabel_35 = new JLabel("Time local :");
		lblNewLabel_35.setBounds(10, 503, 83, 14);
		panel_2.add(lblNewLabel_35);

		textField_TimeLocal_res = new JTextField();
		textField_TimeLocal_res.setColumns(10);
		textField_TimeLocal_res.setBounds(115, 500, 148, 20);
		panel_2.add(textField_TimeLocal_res);

		JLabel lblNewLabel_36 = new JLabel("Date, Local Tran :");
		lblNewLabel_36.setBounds(10, 528, 104, 14);
		panel_2.add(lblNewLabel_36);

		textField_LocalTranRes = new JTextField();
		textField_LocalTranRes.setColumns(10);
		textField_LocalTranRes.setBounds(115, 525, 148, 20);
		panel_2.add(textField_LocalTranRes);

		JLabel lblNewLabel_37 = new JLabel("Date Settle : ");
		lblNewLabel_37.setBounds(10, 553, 77, 14);
		panel_2.add(lblNewLabel_37);

		textField_Datesettleres = new JTextField();
		textField_Datesettleres.setColumns(10);
		textField_Datesettleres.setBounds(115, 550, 148, 20);
		panel_2.add(textField_Datesettleres);

		JLabel lblNewLabel_38 = new JLabel("Merchant Type :");
		lblNewLabel_38.setBounds(286, 423, 104, 14);
		panel_2.add(lblNewLabel_38);

		textField_merchan_res = new JTextField();
		textField_merchan_res.setColumns(10);
		textField_merchan_res.setBounds(411, 420, 148, 20);
		panel_2.add(textField_merchan_res);

		JLabel lblNewLabel_39 = new JLabel("Acquirer ID :");
		lblNewLabel_39.setBounds(286, 398, 83, 14);
		panel_2.add(lblNewLabel_39);

		textField_Acquirer_res = new JTextField();
		textField_Acquirer_res.setColumns(10);
		textField_Acquirer_res.setBounds(411, 395, 148, 20);
		panel_2.add(textField_Acquirer_res);

		JLabel lblNewLabel_40 = new JLabel("Auth Identity res :");
		lblNewLabel_40.setBounds(10, 578, 104, 14);
		panel_2.add(lblNewLabel_40);

		textField_auth_res = new JTextField();
		textField_auth_res.setColumns(10);
		textField_auth_res.setBounds(115, 575, 148, 20);
		panel_2.add(textField_auth_res);

		JLabel lblNewLabel_41 = new JLabel("Terminal ID: ");
		lblNewLabel_41.setBounds(286, 373, 90, 14);
		panel_2.add(lblNewLabel_41);

		textField_Teminal_res = new JTextField();
		textField_Teminal_res.setColumns(10);
		textField_Teminal_res.setBounds(411, 370, 148, 20);
		panel_2.add(textField_Teminal_res);

		textField_adDataN = new JTextField();
		textField_adDataN.setColumns(10);
		textField_adDataN.setBounds(431, 475, 220, 20);
		panel_2.add(textField_adDataN);

		textField_AdDataP = new JTextField();
		textField_AdDataP.setColumns(10);
		textField_AdDataP.setBounds(412, 500, 239, 20);
		panel_2.add(textField_AdDataP);

		JLabel lblNewLabel_43 = new JLabel("Curr Code : ");
		lblNewLabel_43.setBounds(286, 450, 90, 14);
		panel_2.add(lblNewLabel_43);

		textField_Curr_res = new JTextField();
		textField_Curr_res.setColumns(10);
		textField_Curr_res.setBounds(412, 447, 66, 20);
		panel_2.add(textField_Curr_res);

		JLabel lblAccountIdentification = new JLabel("Account identification 2 :");
		lblAccountIdentification.setBounds(273, 553, 148, 14);
		panel_2.add(lblAccountIdentification);

		textField_Acc2 = new JTextField();
		textField_Acc2.setColumns(10);
		textField_Acc2.setBounds(431, 550, 203, 20);
		panel_2.add(textField_Acc2);

		JLabel lblNewLabel_44 = new JLabel("Field 48 [Mini] :");
		lblNewLabel_44.setBounds(20, 655, 95, 20);
		panel_2.add(lblNewLabel_44);

		textField_AddAm_res = new JTextField();
		textField_AddAm_res.setColumns(10);
		textField_AddAm_res.setBounds(136, 603, 606, 23);
		panel_2.add(textField_AddAm_res);

		JLabel lblNewLabel_45 = new JLabel("Additional Amounts : ");
		lblNewLabel_45.setBounds(10, 606, 123, 20);
		panel_2.add(lblNewLabel_45);

		JLabel lblNewLabel_51 = new JLabel("Retrieval Ref :");
		lblNewLabel_51.setBounds(286, 578, 104, 14);
		panel_2.add(lblNewLabel_51);

		textField_retRes = new JTextField();
		textField_retRes.setBounds(412, 575, 151, 20);
		panel_2.add(textField_retRes);
		textField_retRes.setColumns(10);

		JButton btnNewButton_2 = new JButton("Bulk");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isInteger(textFieldBulknumber.getText())) {
					T2PSwing.infoBox("Bulk is not numeric", "Critical error");
				} else {
					if (rbFT.isSelected()) {
						inputs = 0;
						System.out.println("FT" + rbFT.isSelected());
					} else if (rbBI.isSelected()) {
						inputs = 1;
						System.out.println("BE" + rbBI.isSelected());
					} else if (rbMS.isSelected()) {
						inputs = 2;
						System.out.println("MS" + rbMS.isSelected());

					}
					String servername = textField_Server.getText();
					int port = Integer.parseInt(textField_Port.getText());
					int requirednooftransaction = Integer.parseInt(textFieldBulknumber.getText());
					HashMap<String/* Field names */, JTextField/* textFields */> fieldmap = new HashMap<>();
					fieldmap.put("PrimaryBitmap", textField_priBit_res);
					fieldmap.put("BitmapExtended", textField_bt_Ex_res);
					fieldmap.put("Pan", textField_PANRes);
					fieldmap.put("Amount", textField_AmtRes);
					fieldmap.put("TranDate", textField_TranDate_res);
					fieldmap.put("SystemTrace", textField_SysTrace_res);
					fieldmap.put("TimeLocal", textField_TimeLocal_res);
					fieldmap.put("LocalTran", textField_LocalTranRes);
					fieldmap.put("DateSettle", textField_Datesettleres);
					fieldmap.put("AuthCode", textField_auth_res);
					fieldmap.put("AdditionalAm", textField_AddAm_res);
					fieldmap.put("ResponseCode", textField_responseCode);
					fieldmap.put("TerminalId", textField_Teminal_res);
					fieldmap.put("AcquirerID", textField_Acquirer_res);
					fieldmap.put("MerchantType", textField_merchan_res);
					fieldmap.put("RetrievalRef", textField_retRes);
					fieldmap.put("Currency", textField_Curr_res);
					fieldmap.put("AdditionalDataNational", textField_adDataN);
					fieldmap.put("AdditionalDataPriate", textField_AdDataP);
					fieldmap.put("Account1", textField_Acc1);
					fieldmap.put("Account2", textField_Acc2);
					new Thread(() -> {
						BulkRequestor br = new BulkRequestor(servername, Makeiso8583Bulk(), port,
								requirednooftransaction, inputs, editorPane_1, lblNewLabel_28, fieldmap, lblNewLabel_42,
								lblAdditionalDatap, editorPane_2, textField_ProcessCode, textField_messageCode);
						br.start();
					}).start();

				}
			}
		});
		btnNewButton_2.setBounds(578, 20, 66, 23);
		panel_2.add(btnNewButton_2);

		textFieldBulknumber = new JTextField();
		textFieldBulknumber.setBounds(486, 21, 73, 20);
		panel_2.add(textFieldBulknumber);
		textFieldBulknumber.setColumns(10);

		rbBI = new JRadioButton("BI");
		rbBI.setBounds(455, 47, 58, 23);
		panel_2.add(rbBI);

		rbFT = new JRadioButton("FT");
		rbFT.setBounds(395, 47, 52, 23);
		panel_2.add(rbFT);
		rbMS = new JRadioButton("MS");
		rbMS.setBounds(526, 47, 58, 23);
		panel_2.add(rbMS);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbFT);
		buttonGroup.add(rbBI);
		buttonGroup.add(rbMS);

		JButton btnNewButton_3 = new JButton("BOL");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_3.setBounds(578, 47, 56, 23);
		panel_2.add(btnNewButton_3);

		// buttonGroup.add(new JRadioButton('Label2', true));

		JButton btnNewButton_1 = new JButton("Unlock");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (textField_2.getPassword().length == 0) {
					T2PSwing.infoBox("Enter Password", "Empty Error");
				} else if (textField_2.getPassword().length > 1) {
					EncryptAndDecryptAndReturn end = new EncryptAndDecryptAndReturn(
							String.valueOf(textField_2.getPassword()));
					if (end.passwordisCorrect()) {
						passwordmatch = true;
					} else {
						T2PSwing.infoBox("Password incorrect, Tries left :" + (triesleft - 1) + "",
								"Authentication Error");
						triesleft -= 1;
						if (triesleft == 0) {
							System.exit(0);
						}

					}
				} else {
					T2PSwing.infoBox("Error Occured", "Fatal Error");
				}
				if (passwordmatch) {
					lblNewLabel_53.setForeground(Color.GREEN);
					lblNewLabel_53.setText("Unlocked");
					button.setEnabled(true);
					btnNewButton.setEnabled(true);
					button_1.setEnabled(true);
				}
			}

		});
		btnNewButton_1.setBounds(437, 11, 77, 23);
		panel.add(btnNewButton_1);

	}

	public String Makeiso8583() {
		FieldModifier Fm = new FieldModifier();
		String ISOTEXT = textField_messageCode.getText() + textField_primary.getText()
				+ textField_secondarybtmap.getText() + Fm.LLVARString(textField_PAN.getText())
				+ textField_proCode.getText() + Fm.Amount(textField_amount.getText()) + textField_Date.getText()
				+ Fm.AppendrequiredZero(textField.getText(), 6) + textField_1_time.getText()
				+ textField_DateLocal.getText() + textField_DateExp.getText() + textField_1.getText()
				+ Fm.AnyNULLFormat(textField_Merchant.getText(), 4) + Fm.AnyNULLFormat(textField_Service.getText(), 3)
				+ Fm.AnyNULLFormat(textField_condition.getText(), 2) + Fm.LLVARString(textField_Acquirer.getText())
				+ Fm.LLVARString(textField_track.getText()) + Fm.AnyNULLFormat(textField_retrival.getText(), 12)
				+ Fm.AnyNULLFormat(textField_Terminal.getText(), 8)
				+ Fm.AnyNULLFormat(textField_cardidentity.getText(), 15)
				+ Fm.AnyNULLFormat(textField_cardlocation.getText(), 40) + Fm.LLLVARString(textField_field46.getText())
				+ textField_Currency.getText() + Fm.AnyNULLFormat(textField_PIN.getText(), 16)
				+ Fm.LLVARString(textField_Ac1.getText()) + Fm.LLVARString(textField_Ac2.getText())
				+ Fm.LLLVARString(textField_123.getText()) + Fm.LLLVARString(textField_124.getText());
		String FinalIsoText = "0" + ISOTEXT.length() + ISOTEXT;
		return FinalIsoText;
	}

	public String Makeiso8583Bulk() {
		FieldModifier Fm = new FieldModifier();
		TranDate d = new TranDate();
		Random rnd = new Random();
		String ISOTEXT = textField_messageCode.getText() + textField_primary.getText() + textField_secondarybtmap
				.getText() + "191234567890123456789" /* Fm.LLVARString(textField_PAN.getText()) */
				+ textField_proCode.getText() + Fm.Amount(textField_amount.getText()) + textField_Date.getText()
				+ String.valueOf(100000 + rnd.nextInt(900000)) /* random trace each time */ + textField_1_time.getText()
				+ textField_DateLocal.getText() + textField_DateExp.getText() + textField_1.getText() + "6011" + "901"
				+ "00" + Fm.LLVARString(textField_Acquirer.getText()) + Fm.LLVARString(textField_track.getText())
				+ (String.valueOf(d.generateRandom(12)))/*
														 * random retrieval each time
														 */
				+ Fm.AnyNULLFormat(textField_Terminal.getText(), 8)
				+ Fm.AnyNULLFormat(textField_cardidentity.getText(), 15)
				+ Fm.AnyNULLFormat(textField_cardlocation.getText(), 40) + Fm.LLLVARString(textField_field46.getText())
				+ textField_Currency.getText() + Fm.AnyNULLFormat(textField_PIN.getText(), 16)
				+ Fm.LLVARString(textField_Ac1.getText()) + Fm.LLVARString(textField_Ac2.getText())
				+ Fm.LLLVARString(textField_123.getText()) + Fm.LLLVARString(textField_124.getText());
		String FinalIsoText = "0" + ISOTEXT.length() + ISOTEXT;
		return FinalIsoText;
	}

	public String MakeISO8583Reversal() {
		FieldModifier Fm = new FieldModifier();
		String authorization, ResponseCode;
		if (textField_authorization.getText().isEmpty() || textField_authorization.getText().length() < 6) {
			authorization = "123456";
		} else {
			authorization = textField_authorization.getText();
		}
		if (textField_responseCODE.getText().isEmpty()) {
			ResponseCode = "  ";
		} else {
			ResponseCode = textField_responseCODE.getText();
		}
		String ISOTEXT = textField_messageCode.getText() + textField_primary.getText()
				+ textField_secondarybtmap.getText() + Fm.LLVARString(textField_PAN.getText())
				+ textField_proCode.getText() + Fm.Amount(textField_amount.getText()) + textField_Date.getText()
				+ Fm.AppendrequiredZero(textField.getText(), 6) + textField_1_time.getText()
				+ textField_DateLocal.getText() + textField_1.getText()
				+ Fm.AnyNULLFormat(textField_Merchant.getText(), 4) + Fm.AnyNULLFormat(textField_Service.getText(), 3)
				+ Fm.AnyNULLFormat(textField_condition.getText(), 2) + Fm.LLVARString(textField_Acquirer.getText())
				+ Fm.AnyNULLFormat(textField_retrival.getText(), 12) +
				// if(textField_authorization.getText().isEmpty())
				authorization + ResponseCode + Fm.AnyNULLFormat(textField_Terminal.getText(), 8)
				+ Fm.AnyNULLFormat(textField_cardidentity.getText(), 15)
				+ Fm.AnyNULLFormat(textField_cardlocation.getText(), 40) + Fm.LLLVARString(textField_field46.getText())
				+ textField_Currency.getText() + Fm.AppendrequiredZero(textField_OriData.getText(), 42)
				+ Fm.LLVARString(textField_Ac1.getText()) + Fm.LLVARString(textField_Ac2.getText());
		String FinalIsoText = "0" + ISOTEXT.length() + ISOTEXT;
		return FinalIsoText;
	}
}