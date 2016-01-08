package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import gui.*;
import message.Message;

/**
 * Controller for coordinating communication between server, client handlers and GUI.
 * 
 * @author Filip
 *
 */
public class ServerController implements Controller{

	private String userName;
	
	private Server server;
	
	private GUIChat gui;
	
	/**
	 * Creates a server controller
	 * 
	 * @param gui
	 * @param server
	 */
	public ServerController(GUIChat gui, Server server) {
		this.gui = gui;
		userName = "Server";
		this.server = server;
		server.start();
	}

	public void sendMessage(String text) {
		server.send(new Message(text));
	}
	
	public void start() throws ConnectException, SocketTimeoutException, IOException{
		server.start();
	}

	/**
	 * Displays the specified message in the GUI.
	 * 
	 * @param m
	 */
	public void displayMessage(Message m) {
		gui.displayMessage(m);
	}
	
}
