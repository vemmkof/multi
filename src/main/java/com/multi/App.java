package com.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.multi.service.ClientHandler;

/**
 * Hello world!
 *
 */
public class App {
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		String main = Thread.currentThread().getName();
		try {
			serverSocket = new ServerSocket(8080);
			LOGGER.log(Level.INFO, main + " SERVER UP");
			while (true) {
				Socket client = null;
				client = serverSocket.accept();
				String name = new Long(new Date().getTime()).toString();
				Thread t = new ClientHandler(client, name);
				t.setName(name);
				t.start();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (Exception e2) {
					LOGGER.log(Level.SEVERE, e2.getMessage());
				}
			}
		}
	}
}
