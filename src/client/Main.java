package client;

import gui.GUIChat;


public class Main {

	public static void main(String[] args) {
		String serverHost = "";
		int serverPort = 0;
		String userName = "1";
		GUIChat gui = new GUIChat();
		ClientController c = new ClientController(gui, serverHost, serverPort, userName);
		gui.Start(c, "Chat");
	}

}
