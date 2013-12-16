package bo.project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import bo.project.message.Message;
import bo.project.message.MessageType;

public class MessageReceiver extends Thread{

        private RequestQueue requestQueue;
        private ResponseQueue responseQueue;
        private ServerSocket serverSocket;
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private int identifier = 1;
        
        public MessageReceiver(int port, int maxQueueSize) throws IOException {
                try {
                        this.requestQueue = new RequestQueue(maxQueueSize);
                        this.responseQueue = new ResponseQueue(maxQueueSize);
                        this.serverSocket = new ServerSocket(port);
                        Runtime.getRuntime().addShutdownHook(new Thread() {
                                @Override
                                public void run() {
                                        try {
                                                serverSocket.close();
                                        } catch (IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                        }
                                }
                        });
                } catch (IOException e) {
                        System.err.println("[ClientProxy error] Cannot connect: " + e.getMessage());
                        throw e;
                }
        }
        
        public Message getRequest() {
                return this.requestQueue.pollRequest();
        }
        
        public void addResponse(Message response) {
        	this.responseQueue.addResponse(response);
        }

        @Override
        public void run() {
                while (true) {
                        try {
                                clientSocket = serverSocket.accept();
                                out = new ObjectOutputStream(clientSocket.getOutputStream());
                                in = new ObjectInputStream(clientSocket.getInputStream());                                
                                
                                Message message = (Message) in.readObject();
                                
                                if (message.getType() == MessageType.SOL_REQUEST) {
                                        System.out.println("[Server] Request received.");
                                        message.setId(identifier);
                                        ++identifier;
                                        requestQueue.addRequest(message);
                                        message.setType(MessageType.SOL_ID);
                                        out.writeObject(message);
                                } else if (message.getType() == MessageType.SOL_ASK) {
                                        System.out.println("[Server] Ask received.");
                                        Message response = responseQueue.getById(message.getId());
                                        if (response != null) {
                                                out.writeObject(response);
                                        } else {
                                                message.setType(MessageType.SOL_INCOMPLETE);
                                                out.writeObject(message);
                                        }
                                } 
                                
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } finally {
                                try {
                                        clientSocket.close();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                }
        }
}