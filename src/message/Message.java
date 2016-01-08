package message;

import java.io.Serializable;

/**
 * A class representing a message.
 * 
 * @author Filip
 *
 */
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	/**
	 * Creates a message
	 * 
	 * @param s
	 */
	public Message(String s){
		text = s;
	}

	/**
	 * Gets the text from the message.
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
	
}
