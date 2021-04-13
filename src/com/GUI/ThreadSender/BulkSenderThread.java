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

public class BulkSenderThread extends Thread {
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
	InputStream in;
	OutputStream out;
	int count = 0;
	int inputs;
	/* Create Socket here only */
	private String servername;
	private int port;

	public BulkSenderThread(int count, Socket socket, String IsoText, JEditorPane editorPane_1, JLabel successful,
			HashMap<String/* Field names */, JTextField/* textFields */> fieldmap/* switch* labels for reversal */,
			JLabel lblNewLabel_42, JLabel lblAdditionalDatap, int inputs) {
		this.socket = socket;
		this.IsoText = IsoText;
		this.editorPane_1 = editorPane_1;
		this.successful = successful;
		this.fieldmap = fieldmap;
		this.lblNewLabel_42 = lblNewLabel_42;
		this.lblAdditionalDatap = lblAdditionalDatap;
		this.count = count - 1;
		this.inputs = inputs;
	}

	public BulkSenderThread(String servername, int port, int count, String makeiso8583Bulk, JEditorPane editorPane_1,
			JLabel successful, HashMap<String, JTextField> fieldmap, JLabel lblNewLabel_42, JLabel lblAdditionalDatap,
			int inputs) {
		this.servername = servername;
		this.port = port;
		this.IsoText = makeiso8583Bulk;
		this.editorPane_1 = editorPane_1;
		this.successful = successful;
		this.fieldmap = fieldmap;
		this.lblNewLabel_42 = lblNewLabel_42;
		this.lblAdditionalDatap = lblAdditionalDatap;
		this.count = count - 1;
		this.inputs = inputs;
	}

	public void Field48(JEditorPane editorPane_2, JTextField textField_ProcessCode, JTextField textField_messageCode) {
		this.editorPane_2 = editorPane_2;
		this.textField_ProcessCode = textField_ProcessCode;
		this.textField_messageCode = textField_messageCode;
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

	public boolean ifresponsereceived() {
		return returned;
	}

	@Override
	public void run() {
		try {
			sendrequest();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void sendrequest() throws IOException, InterruptedException {
		if (inputs == 0) {
			sendrequestfundtransfer();
		} else if (inputs == 1) {
			sendrequestBalanceenquiry();
		} else {
			sendrequestMinistatement();
		}
	}

	public void sendrequestfundtransfer() throws IOException, InterruptedException {
		while (count != 0) {
			sendfundrecursive();
			count--;
		}

	}

	public void sendfundrecursive() throws UnknownHostException, IOException {
		socket = new Socket(servername, port);
		OutputStream outs = socket.getOutputStream();
		String processCodeChanged = fundtransferProcessCode(this.IsoText);
		byte[] be = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		outs.write(be);
		outs.flush();
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
		}
	}

	public void sendrequestBalanceenquiry() throws IOException, InterruptedException {
		String processCodeChanged = BalanceEnquiryProcessCode(this.IsoText);
		byte[] b = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		out.write(b);
		out.flush();
		System.out.println(count);
		// out.close();
		String ss = null;
		ss = getresponsefromListener();
		while (count != 0) {
			count--;
			if (!ss.isEmpty()) {
//				sendrequest();
				sendrequest();
			}
		}
	}

	public void sendrequestMinistatement() throws IOException, InterruptedException {
		String processCodeChanged = MiniStatementProcessCode(this.IsoText);
		byte[] b = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		out.write(b);
		out.flush();
		System.out.println(count);
		// out.close();
		String ss = null;
		ss = getresponsefromListener();
		while (count != 0) {
			count--;
			if (!ss.isEmpty()) {
//				sendrequest();
				sendrequest();
			}
		}
	}

	public String getresponsefromListener() throws IOException {
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
		} /*
			 * finally { // releases system resources associated with this stream if (in !=
			 * null) in.close(); out.close(); }
			 */
		return finalISO.toString();
	}

}
