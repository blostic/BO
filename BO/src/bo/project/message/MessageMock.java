package bo.project.message;

import java.io.Serializable;

public class MessageMock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private MessageType type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
}
