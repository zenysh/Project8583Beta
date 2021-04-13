package com.GUI.ThreadSender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.GUI.ResponseFields.ISOBreakDown;

public class BulkSenderOwnListner extends Thread {
	private Socket socket;
	private String IsoText;

	JTextField textField_messageCode;
	boolean returned = false;
	InputStream in;
	OutputStream out;
	int count = 0;
	int inputs;
	private String servername;
	private int port;
	private Socket sock;

	public BulkSenderOwnListner(String servername, int port, int count, String IsoText, int inputs) {
		this.IsoText = IsoText;
		this.count = count - 1;
		this.inputs = inputs;
		this.servername = servername;
		this.port = port;
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
		/*
		 * sendfund(); while (count != 0) { if (getresponsefromListener() != null) {
		 * sendfund(); } count--; }
		 */
		while (count != 0) {
			sendfundrecursive();
			count--;
		}
	}

	public void sendfund() throws IOException, InterruptedException {
		String processCodeChanged = fundtransferProcessCode(this.IsoText);
		byte[] b = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		out.write(b);
		out.flush();
	}

	public void sendfundrecursive() throws IOException, InterruptedException {
		sock = new Socket(servername, port);
		OutputStream outs = sock.getOutputStream();
		String processCodeChanged = fundtransferProcessCode(this.IsoText);
		byte[] be = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		outs.write(be);
		outs.flush();
		InputStream ins = sock.getInputStream();
		int i;
		char c;
		String charLength = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder finalISO = new StringBuilder();
		try {
			while ((i = ins.read()) != -1) {
				charLength = sb.append((char) i).toString();
				if (charLength.length() == 4) {
					break;
				}
			}
			byte[] buffer = new byte[Integer.parseInt(charLength)];
			ins.read(buffer);
			for (byte b : buffer) {
				c = (char) b;
				finalISO.append(c);
			}
			finalISO.insert(0, charLength);
		} catch (Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
		System.out.println(finalISO.toString().length() + "leng iso");
		System.out.println(charLength + "char iso");
		// if (finalISO.toString().length() - 4 == Integer.parseInt(charLength)) {
		// sendfundrecursive();
		// }

	}

	public void sendrequestBalanceenquiry() throws IOException, InterruptedException {
		String processCodeChanged = BalanceEnquiryProcessCode(this.IsoText);
		byte[] b = ChangeTraceAndRetrievalEverytime(processCodeChanged).getBytes(StandardCharsets.UTF_8);
		out.write(b);
		out.flush();
		System.out.println(count);
		// out.close();
		String ss = null;
		while (count != 0) {
			count--;
			ss = getresponsefromListener();
			if (!ss.isEmpty()) {
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
			while ((i = in.read()) != -1) {
				charLength = sb.append((char) i).toString();
				if (charLength.length() == 4) {
					break;
				}
			}
			byte[] buffer = new byte[Integer.parseInt(charLength)];
			in.read(buffer);
			for (byte b : buffer) {
				c = (char) b;
				finalISO.append(c);
			}
			finalISO.insert(0, charLength);
		} catch (Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
		return finalISO.toString();
	}

}