package com.multi.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.multi.singleton.Data;

public class ClientHandler extends Thread {
	private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String key;

	/**
	 * @param socket
	 */
	public ClientHandler(Socket socket, String key) {
		this.socket = socket;
		this.key = key;
	}

	@Override
	public void run() {
		LOGGER.log(Level.INFO, "Thread " + key + " started");
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				String received = dis.readUTF();
				LOGGER.log(Level.INFO, "Thread[" + key + "] get: " + received);
				String toSend = new StringBuffer(received).reverse().toString();
				LOGGER.log(Level.INFO, "Thread[" + key + "] set: " + toSend);
				dos.writeUTF(toSend);
				Data.getInstance().getMessages().put(key, toSend);
				LOGGER.log(Level.INFO, "Thread[" + key + "] singleton: " + Data.getInstance().getMessages());
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			try {
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e1) {
				LOGGER.log(Level.SEVERE, e1.getMessage());
			}
		}
	}

}
