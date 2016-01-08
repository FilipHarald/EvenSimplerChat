package gui;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import message.Message;

/**
 * Controller for coordinating in- and outputstreams with the GUI. (used both on server and clientside)
 * @author Filip
 *
 */
public interface Controller {
	/**
	 * Sends a message with the specified text.
	 * 
	 * @param text
	 */
	public void sendMessage(String text);
	/**
	 * Starts the controller.
	 * 
	 * @throws ConnectException
	 * @throws SocketTimeoutException
	 * @throws IOException
	 */
	public void start() throws ConnectException, SocketTimeoutException, IOException;
}
