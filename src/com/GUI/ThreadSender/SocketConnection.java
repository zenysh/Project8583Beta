package com.GUI.ThreadSender;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketConnection extends Thread {
	private Socket socket;
	private String server;
	int port;
	public SocketConnection(Socket socket, String server, int port) {
		this.socket = socket;
		this.server = server;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(server, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
