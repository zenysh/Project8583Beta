package com.GUI.ThreadSender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.GUI.ResponseFields.ISOBreakDown;

public class ISOSender extends Thread {
	private Socket socket;
	private String IsoText;
	private JEditorPane editorPane_1;
	private JEditorPane editorPane_2 = null;
	JLabel successful;
	JTextField textField_ProcessCode;
	HashMap<String, JTextField> fieldmap;
	JLabel lblNewLabel_42;
	JLabel lblAdditionalDatap;
	JTextField textField_messageCode;
	boolean returned = false;

	public ISOSender(Socket socket, String IsoText, JEditorPane editorPane_1, JLabel successful,
			HashMap<String/* Field names */, JTextField/* textFields */> fieldmap/* switch* labels for reversal */,
			JLabel lblNewLabel_42, JLabel lblAdditionalDatap) {
		this.socket = socket;
		this.IsoText = IsoText;
		this.editorPane_1 = editorPane_1;
		this.successful = successful;
		this.fieldmap = fieldmap;
		this.lblNewLabel_42 = lblNewLabel_42;
		this.lblAdditionalDatap = lblAdditionalDatap;
	}

	public void Field48(JEditorPane editorPane_2, JTextField textField_ProcessCode, JTextField textField_messageCode) {
		this.editorPane_2 = editorPane_2;
		this.textField_ProcessCode = textField_ProcessCode;
		this.textField_messageCode = textField_messageCode;
	}

	public boolean ifresponsereceived() {
		return returned;
	}

	@Override
	public void run() {
		OutputStream outputStream;
		try {
			String resp = null;
			outputStream = socket.getOutputStream();
			byte[] b = this.IsoText.getBytes(StandardCharsets.UTF_8);
			outputStream.write(b);/* Send request to server with outputStream. */
			getResponse(socket, outputStream);

			/*
			 * Get response in single byte, convert it to Char and append to single String
			 * When Everything is done/finished, finally close input,outputStream.
			 */
			// outputStream.close();
			socket.close();// Close Socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String request =
		// "02730200F23E448128E49000000000000600003009123456789400000000000002500012721031098998021044001270127012760119010008155481180020212109808915548118
		// 000524 23001001001000062000000012300100100100006704000001000000";

	}

	public String getResponse(Socket socket, OutputStream outputStream) throws IOException {
		// InputStream in = new ByteArrayInputStream(sock.getInputStream().getBytes());
		InputStream in = null;
		int i;
		// byte[] buffer = new byte[250];
		char c;
		String charLength = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder finalISO = new StringBuilder();
		try {
			// new input stream created
			in = socket.getInputStream();
			while ((i = in.read()) != -1) {
				charLength = sb.append((char) i).toString();
				if (charLength.length() == 4) {
					break;
				}
			}
			byte[] buffer = new byte[Integer.parseInt(charLength)];
			// read stream data into buffer
			in.read(buffer);

			// for each byte in the buffer
			for (byte b : buffer) {
				// convert byte to character
				c = (char) b;
				finalISO.append(c);
				// prints character
				// System.out.print(c);
			}
			finalISO.insert(0, charLength);
			editorPane_1.setText(finalISO.toString());
			if (finalISO != null) {
				successful.setText("Successful");
				// Thread.sleep(1_000);
				ISOBreakDown isBreak = new ISOBreakDown(finalISO.toString());
				if (textField_messageCode.getText().equals("0420")) {
					isBreak.setFieldsReversal();
				} else {
					isBreak.SetFields();
				}
				// isBreak.SetFields();
				fieldmap.get("PrimaryBitmap").setText(isBreak.getPrimaryBitmap());
				fieldmap.get("BitmapExtended").setText(isBreak.getBitMapExtended());
				fieldmap.get("Pan").setText(isBreak.getPAN());
				fieldmap.get("Amount").setText(isBreak.getAmount());
				fieldmap.get("TranDate").setText(isBreak.getTranDateAndTime());
				fieldmap.get("SystemTrace").setText(isBreak.getSystemTrace());
				fieldmap.get("TimeLocal").setText(isBreak.getTimelocalTran());
				fieldmap.get("LocalTran").setText(isBreak.getDateLocalTran());
				fieldmap.get("DateSettle").setText(isBreak.getDatesettle());
				fieldmap.get("AuthCode").setText(isBreak.getAuthResponsel());
				fieldmap.get("AdditionalAm").setText(isBreak.getAdditionalAmounts());
				fieldmap.get("ResponseCode").setText(isBreak.getResponseCode());
				fieldmap.get("TerminalId").setText(isBreak.getTerminalId());
				fieldmap.get("AcquirerID").setText(isBreak.getAcquierID());
				fieldmap.get("MerchantType").setText(isBreak.getMerchantType());
				fieldmap.get("Currency").setText(isBreak.getCurrencyCode());
				fieldmap.get("RetrievalRef").setText(isBreak.getRetrievalReference());
				fieldmap.get("AdditionalDataNational").setText(isBreak.getAdditionaDataNational());
				fieldmap.get("AdditionalDataPriate").setText(isBreak.getAdditionaDataPrivate());
				fieldmap.get("Account1").setText(isBreak.getAccount1());
				fieldmap.get("Account2").setText(isBreak.getAccount2());
				if (textField_messageCode.getText().equals("0420")) {
					lblNewLabel_42.setText("Card acceptor code: ");
					lblAdditionalDatap.setText("Card location: ");
					fieldmap.get("AdditionalDataNational").setText(isBreak.getCardAcceptorIdentification());
					fieldmap.get("AdditionalDataPriate").setText(isBreak.getCardAcceptorname());
				}
				if (editorPane_2 != null && textField_ProcessCode.getText().equals("350000")) {
					editorPane_2.setText(isBreak.getAdditionaDataPrivate());
				} else {
					editorPane_2.setEnabled(false);
				}

			}
		} catch (Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		} finally {
			// releases system resources associated with this stream
			if (in != null)
				in.close();
			outputStream.close();
		}

		return finalISO.toString();

	}

}
