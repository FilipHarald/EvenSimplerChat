package server;

import java.io.IOException;

import gui.GUIChat;
public class Main {

	public static void main(String[] args) {
		int serverPort = 0;
		String userName = "1";
		GUIChat gui = new GUIChat();
		Server server;
		try {
			server = new Server(8080);
			ServerController c = new ServerController(gui, server);
			server.setController(c);
			gui.Start(c, "Server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
