package bo.project.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import bo.project.message.Message;
import bo.project.message.MessageType;

public class ServerProxy {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String host = "localhost";
	private int port = 6000;

	public Message sendMessage(Message message) {
		try {
			socket = new Socket(this.host, this.port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in  = new ObjectInputStream(socket.getInputStream());

			out.writeObject(message);
			System.out.println("[Client] Message sent");

			return (Message) in.readObject();
		} catch (UnknownHostException e) {
			System.err.println("[ServerProxy error] Cannot connect - unknown host.");
			return null;
		} catch (IOException e) {
			System.out.println("[ServerProxy error] Cannot connect - connection refused.");
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Message askForSolution(int id) {
		Message message = new Message();
		message.setType(MessageType.SOL_ASK);
		return sendMessage(message);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}


}