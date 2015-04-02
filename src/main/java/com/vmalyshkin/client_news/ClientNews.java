package com.vmalyshkin.client_news;

import com.vmalyshkin.client_news.frameClient.ClientFrame;
import com.vmalyshkin.client_news.frameClient.panelsClient.NewsPanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNews {
	private static String serverSockets = "localhost";
	private static int port = 5679;

	private static final String TEXT_1 = "================================================================= \n";
	private static final String TEXT_2 = "Программа News Messages. Клинтская часть. Автор Виктор Малышкин \n";
	private static final String TEXT_3 = "Введите IP сервера, или IP сервера и номер порта! \n" + "или \n" + "...\\java -jar Admin.jar [IP]  \n" + "...\\java -jar Admin.jar [IP] [#port] \n";
	private static final String TEXT_4 = "По умолчанию IP сервера "+serverSockets+", номер порта "+port+" \n";
	private static final String TEXT_5 = "Программа запущена!!! \n";
	private static final String TEXT_6 = "IP сервер успешно введён! \n IP сервер: " + serverSockets + " \n";
	private static final String TEXT_7 = "IP сервер и номер порта успешно введены! \n" + "IP сервер: " + serverSockets + " \n" + "Номер порта: " + port + " \n";

	public static void main(String[] args) {
		if (args.length > 0) {
			if (args.length > 0 && args.length < 2) {
				if (args[0].equals("-help")) {
					System.out.println(TEXT_1 + TEXT_3 + TEXT_4 + TEXT_1);
				} else {
					serverSockets = args[0];
					System.out.println(TEXT_1 + TEXT_6 + TEXT_5 + TEXT_1);
					startProgram();
				}
			} else if (args.length > 1) {
				serverSockets = args[0];
				port = Integer.parseInt(args[1]);
				System.out.println(TEXT_1 + TEXT_7 + TEXT_5 + TEXT_1);
				startProgram();
			}
		} else {
			System.out.println(TEXT_1 + TEXT_2 + TEXT_3 + TEXT_4 + TEXT_5 + TEXT_1);
			startProgram();
		}
	}
	public static void startProgram() {
		new ClientFrame("Client", new Dimension(655, 470));

		try (Socket socket = new Socket(InetAddress.getByName(serverSockets), port)) {
			while (true) {
				InputStreamReader in = new InputStreamReader(socket.getInputStream());

				if (in.ready()) {
					String s = "";
					while (in.ready()) {
						char c = (char) in.read();
						s += c;
					}
					NewsPanel.appendNewsTextArea(s + "\n\n");
					System.out.println(s);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("ERROR ClientNews - \"Thread.sleep(2000);\"");
					}
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("Socket Соединение localhost не удалось!!!");
		} catch (IOException e) {
			String error = "ServerSocket Соединение не удалось!!!";
			NewsPanel.appendNewsTextArea(error + "\n");
			System.out.println(error);
		}
	}
}


