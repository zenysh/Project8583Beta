package com.GUI.ThreadSender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.GUI.ResponseFields.ISOBreakDown;

public class BulkRequestor extends Thread {
	public String servername;
	public int port;
	public Socket socket;
	public int requirednooftransaction;
	public int typeoftransaction;
	private String ISOText;
	/* Extra Fields */
	private JEditorPane editorPane_1;
	private JEditorPane editorPane_2 = null;
	JLabel successful;
	JTextField textField_ProcessCode;
	HashMap<String, JTextField> fieldmap;
	JLabel lblNewLabel_42;
	JLabel lblAdditionalDatap;
	JTextField textField_messageCode;

	public BulkRequestor(String servername, String ISOText, int port, int requirednooftransaction,
			int typeoftransaction/* extra Field Startss */, JEditorPane editorPane_1, JLabel successful,
			HashMap<String/* Field names */, JTextField/* textFields */> fieldmap/* switch* labels for reversal */,
			JLabel lblNewLabel_42, JLabel lblAdditionalDatap
			/* Field 48 here only */, JEditorPane editorPane_2, JTextField textField_ProcessCode,
			JTextField textField_messageCode) {

		this.servername = servername;
		this.port = port;
		this.requirednooftransaction = requirednooftransaction;
		this.typeoftransaction = typeoftransaction;
		this.ISOText = ISOText;
		this.editorPane_1 = editorPane_1;
		this.successful = successful;
		this.fieldmap = fieldmap;
		this.lblNewLabel_42 = lblNewLabel_42;
		this.lblAdditionalDatap = lblAdditionalDatap;
		this.editorPane_2 = editorPane_2;
		this.textField_ProcessCode = textField_ProcessCode;
		this.textField_messageCode = textField_messageCode;

	}

	@Override
	public void run() {
		try {
			sendFundtransfer();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendFundtransfer() throws UnknownHostException, IOException, InterruptedException {
		if (typeoftransaction == 0) {
			while (requirednooftransaction != 0) {
				sendrequest(fundtransferProcessCode(ISOText));
				Thread.sleep(500);// sleep 0.5 sec before sending another request
				requirednooftransaction--;
			}
		} else if (typeoftransaction == 1) {
			while (requirednooftransaction != 0) {
				sendrequest(BalanceEnquiryProcessCode(ISOText));
				Thread.sleep(500);// sleep 0.5 sec before sending another request
				requirednooftransaction--;
			}
		} else {
			while (requirednooftransaction != 0) {
				sendrequest(MiniStatementProcessCode(ISOText));
				Thread.sleep(600);// sleep 0.6 sec before sending another request
				requirednooftransaction--;
			}
		}
	}

	public String fundtransferProcessCode(String ISOMessage) {
		StringBuilder sbft = new StringBuilder(ISOMessage);
		sbft.replace(61, 67, "400000");
		return sbft.toString();
	}

	public String BalanceEnquiryProcessCode(String ISOMessage) {
		StringBuilder sbbe = new StringBuilder(ISOMessage);
		sbbe.replace(61, 67, "301000");
		return sbbe.toString();
	}

	public String MiniStatementProcessCode(String ISOMessage) {
		StringBuilder sbms = new StringBuilder(ISOMessage);
		sbms.replace(61, 67, "350000");
		return sbms.toString();
	}

	public void sendrequest(String ISOmessage) throws UnknownHostException, IOException {
		socket = new Socket(servername, port);
		OutputStream outs = socket.getOutputStream();
		byte[] be = ChangeTraceAndRetrievalEverytime(ISOmessage).getBytes(StandardCharsets.UTF_8);
		outs.write(be);
		outs.flush();
		InputStream in = socket.getInputStream();
		int i;
		char c;
		String charLength = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder finalISO = new StringBuilder();
		try {
			// new input stream created
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
			outs.close();
			socket.close();
		}
	}

	public String ChangeTraceAndRetrievalEverytime(String ISOtext) {
		Random rnd = new Random();
		StringBuilder ssb = new StringBuilder(ISOtext);
		ssb.replace(89, 95, String.valueOf(100000 + rnd.nextInt(900000)));
		ssb.replace(134, 146, (String.valueOf(generateRandom(12))));
		return ssb.toString();
	}

	public long generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
}
