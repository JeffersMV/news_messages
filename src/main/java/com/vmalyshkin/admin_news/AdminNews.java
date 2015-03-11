package com.vmalyshkin.admin_news;

import com.vmalyshkin.admin_news.frameAdmin.NewsFrame;
import com.vmalyshkin.admin_news.threadsSocket.ThreadsSocket;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminNews {
	private static int portServerSocket = 5679;
	private static int numberSockets = 10;

	private static final String TEXT_1 = "================================================================= \n";
	private static final String TEXT_2 = "Программа News Messages. Серверная часть. Автор Виктор Малышкин \n";
	private static final String TEXT_3 = "Введите номер порта, или номер порта и колличество сокетов! \n" + "...\\java -jar Admin.jar [#port]  \n" + "или \n" + "...\\java -jar Admin.jar [#port] [#sockets] \n";
	private static final String TEXT_4 = "По умолчанию номер порта 5678, колличество сокетов 10 \n";
	private static final String TEXT_5 = "Программа запущена!!! \n";
	private static final String TEXT_6 = "Порт успешно введён! Номер порта: \n" + portServerSocket + " \n";
	private static final String TEXT_7 = "Порт и колличество сокетов успешно введены! \n" + "Номер порта: " + portServerSocket + " \n" + "Колличество сокетов: " + numberSockets + " \n";
	private static final String TEXT_8 = "Введите ...\\java -jar Admin.jar -help для справки!";

	public static void main(String[] args) {
		if (args.length > 0) {
			if (args.length > 0 && args.length < 2) {
				if (args[0].equals("-help")) {
					System.out.println(TEXT_1 + TEXT_3 + TEXT_4 + TEXT_1);
				} else {
					portServerSocket = Integer.parseInt(args[0]);
					System.out.println(TEXT_1 + TEXT_6 + TEXT_5 + TEXT_1);
					startProgram();
				}

			} else if (args.length > 1) {
				portServerSocket = Integer.parseInt(args[0]);
				numberSockets = Integer.parseInt(args[1]);
				System.out.println(TEXT_1 + TEXT_7 + TEXT_5 + TEXT_1);
				startProgram();
			}
		} else {
			System.out.println(TEXT_1 + TEXT_2 + TEXT_3 + TEXT_4 + TEXT_5 + TEXT_1);
			startProgram();
		}
	}

	public static void startProgram() {
		try (ServerSocket serverSocket = new ServerSocket(portServerSocket, numberSockets)) {
			new NewsFrame("ADMIN", new Dimension(655, 470));
			while (true) {
				System.out.println("Ищем новое соединение!!!");
				Socket socket = serverSocket.accept();
				socket.getOutputStream().write("Соединение с сервером установлено! \n".getBytes());
				new ThreadsSocket(socket).start();
			}
		} catch (IOException e) {
			System.out.println("Внимание, порт: "+portServerSocket+" занят!!! Введите новый номер порта. \n" + TEXT_8);
		}
	}

}