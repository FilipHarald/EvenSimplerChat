package message;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public Message(String s){
		text = s;
	}

	public String getText() {
		return text;
	}
	
}
