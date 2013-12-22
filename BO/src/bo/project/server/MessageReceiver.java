package bo.project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import bo.project.message.Message;
import bo.project.message.MessageType;

/**
 * Client messages handler
 * 
 * @author Marcin
 *
 */
public class MessageReceiver extends Thread{

	private RequestQueue requestQueue;
	private ResponseQueue responseQueue;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int identifier = 1;
	private boolean isAlive = true;

	/**
	 * Constructor
	 * 
	 * @param port			port on which server listens for incoming connections
	 * @param maxQueueSize	maximum size of request and response queue size
	 * @throws IOException
	 */
	public MessageReceiver(int port, int maxQueueSize) throws IOException {
		try {
			this.requestQueue = new RequestQueue(maxQueueSize);
			this.responseQueue = new ResponseQueue(maxQueueSize);
			this.serverSocket = new ServerSocket(port);
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						if (serverSocket != null && !serverSocket.isClosed()) {
							serverSocket.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (IOException e) {
			Main.error("Cannot connect: " + e.getMessage(),false);
			throw e;
		}
	}
	
	public int getRequestQueueSize() {
		return this.requestQueue.getSize();
	}
	
	public int getResponseQueueSize() {
		return this.responseQueue.getSize();
	}

	/**
	 * Gets first request from queue
	 * 
	 * @return request to process
	 */
	public Message getRequest() {
		return this.requestQueue.pollRequest();
	}

	public void addResponse(Message response) {
		this.responseQueue.addResponse(response);
	}

	/** 
	 * Safe thread stop
	 */
	public void close() {
		this.isAlive = false;
	}

	@Override
	public void run() {
		while (this.isAlive) {
			try {
				clientSocket = serverSocket.accept();
				out = new ObjectOutputStream(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());                                

				Message message = (Message) in.readObject();

				if (message.getType() == MessageType.SOL_REQUEST) {
					Main.info("Request received.");
					message.setId(identifier);
					
					/* Identifier must be unique */
					++identifier;
					requestQueue.addRequest(message);
					
					/* Sending ID to client */
					message.setType(MessageType.SOL_ID);
					out.writeObject(message);
					
				} else if (message.getType() == MessageType.SOL_ASK) {
					Main.info("Ask received.");
					Message response = responseQueue.getById(message.getId());
					if (response != null) {
						out.writeObject(response);
					} else {
						message.setType(MessageType.SOL_INCOMPLETE);
						out.writeObject(message);
					}
				} 

			} catch (SocketException e) {
				// nothing to do - server is closing
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (clientSocket != null && !clientSocket.isClosed())
						clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Used in shutdown hook for closing socket
	 */
	public void closeConnection() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}