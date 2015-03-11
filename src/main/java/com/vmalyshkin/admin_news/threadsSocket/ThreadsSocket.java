package com.vmalyshkin.admin_news.threadsSocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ThreadsSocket extends Thread {
	private Socket socket;
	private static String news;

	public ThreadsSocket(Socket socket) {
		this.socket = socket;
	}

	public ThreadsSocket(String news) {
		this.news = news;
	}

	@Override
	public void run() {
		try {
			String s2 = String.valueOf(socket);
			System.out.println(s2);
			String temp = null;
			while (true) {
				if (news != null) {
					if (!(temp == news)) {
						byte b[] = news.getBytes();
						socket.getOutputStream().write(b);
						socket.getOutputStream().flush();
						temp = news;
					}
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("ERROR ThreadsSocket - \"Thread.sleep(2000);\"");
				}
			}
		} catch (SocketException se) {
			System.out.println("Соединение " + socket + " недоступно!!!");
		} catch (IOException e) {
			System.out.println("ERROR ThreadsSocket!!!");
		}
	}
}
