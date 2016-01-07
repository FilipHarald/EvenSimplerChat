package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import gui.*;
import message.Message;

public class ClientController implements Controller{

	private String serverHost;
	private int serverPort;
	private String userName;
	private Socket socket;
	
	private ObjectOutputStream outputStream;
	
	private GUIChat gui;
	
	public ClientController(GUIChat gui, String serverHost, int serverPort, String userName) {
		super();
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.userName = userName;
		this.gui = gui;
	}
	
	public void sendMessage(String text) {
		if (outputStream != null) {
			try {
				outputStream.writeObject(new Message(text));
				outputStream.flush();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void start() throws ConnectException, SocketTimeoutException, IOException{
		socket = new Socket();
		socket.connect(new InetSocketAddress(serverHost, serverPort), 2000);
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		new MessageListener(ois).start();
	}
	
	private class MessageListener extends Thread {

		private ObjectInputStream inputStream;

		public MessageListener(ObjectInputStream inputStream) {
			this.inputStream = inputStream;
		}

		public Message getMessage() throws IOException{
			try {
				Object obj = inputStream.readObject();
				if (obj instanceof Message) {
					return (Message)obj;
				} else {
					System.out.println("Received object is not of type Message");
				}
			} catch (ClassNotFoundException ex) {
				System.out.println("Received object is not available on client");
			}
			return null;
		}

		@Override
		public void run() {
			
			try {
                Message message;

                while (!Thread.interrupted()) {
                    message = getMessage();
                    gui.displayMessage(message);
                }

			} catch (IOException ex) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
