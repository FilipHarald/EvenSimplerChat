package gui;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import message.Message;

public interface Controller {
	public void sendMessage(String text);
	public void start() throws ConnectException, SocketTimeoutException, IOException;
}
