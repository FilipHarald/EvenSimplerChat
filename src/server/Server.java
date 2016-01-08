package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import message.Message;

/**
 * A class representing a server.
 * 
 * @author Filip
 *
 */
public class Server extends Thread{

	private ServerController controller;
	private ServerSocket serverSocket;
	private List<ClientHandler> clientList;
	private ExecutorService threadPool = Executors.newFixedThreadPool(3);

	/**
	 * Creates a server with the specified port.
	 * 
	 * @param port
	 * @throws IOException
	 */
	public Server(int port) throws IOException {
		clientList = new CopyOnWriteArrayList<ClientHandler>();
		serverSocket = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		System.out.println("Server listening");
		while (!Thread.interrupted()){
			try {
				Socket socket = serverSocket.accept();
				ClientHandler cH = new ClientHandler(socket, this, controller);
				clientList.add(cH);
				cH.start();
				controller.displayMessage(new Message("User connected"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sends a message to all connecting clients.
	 * 
	 * @param message
	 */
	public void send(Message message) {
		threadPool.execute(new MessageSender(message));
	}
	

	private class MessageSender implements Runnable {
		private Message message;

		public MessageSender(Message message) {
			this.message = message;
		}

		public void run() {
			for(ClientHandler cH : clientList){
				cH.sendMessage(message);
			}
		}
	}

	/**
	 * Specifies the controller for the server.
	 * 
	 * @param c
	 */
	public void setController(ServerController c) {
		controller = c;
	}

}
