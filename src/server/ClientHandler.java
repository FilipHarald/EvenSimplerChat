package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.Message;

/**
 * A class representing a connection with one client. With both in- and outputstream. (Used by the server)
 * 
 * @author Filip
 *
 */
public class ClientHandler extends Thread{
	private Socket socket;
	private Server server;
	private ObjectOutputStream oos;
	private ServerController controller;

	/**
	 * Creates a client handler
	 * @param socket
	 * @param server
	 * @param controller
	 */
	public ClientHandler(Socket socket, Server server, ServerController controller) {
		this.socket = socket;
		this.server = server;
		this.controller = controller;
	}

	/**
	 * Sends the specified message to the client.
	 * 
	 * @param message
	 */
	public synchronized void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message m = null;
			while(!Thread.interrupted()){
				try {
					m = (Message) ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				controller.displayMessage(m);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	

}
