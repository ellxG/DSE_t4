package task4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerRequestHandler implements Runnable{
	Invoker invoker;
	ServerSocket server;
	int port;
	Boolean shutdown;
	public ServerRequestHandler(int port) {
		invoker = new Invoker();
		this.port = port;
	};

	@Override
	public void run() {
		shutdown = false;
	    try (ServerSocket server = new ServerSocket(port)) {
	        System.out.println("Server running on port " + port);
	        server.setSoTimeout(1000);
	        
	        while (!shutdown) {
	            try (Socket socket = server.accept()){
	                DataInputStream din = new DataInputStream(socket.getInputStream());
	                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

                    int length = din.readInt();  // == reading 4 bytes
                    byte[] request = new byte[length];
                    din.readFully(request);

                    byte[] response = invoker.handleRequest(request);

                    dout.writeInt(response.length); // == writing 4 bytes
                    dout.write(response);
                    dout.flush(); 
                    
	            } catch (SocketTimeoutException e) {
	            	if (shutdown) {
	            		System.out.println("Server shutdown.");
	            		return;
	            	}
	            } catch (IOException e) {
	                e.printStackTrace();
	            } 
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	} 
	
	public void shutdownServer() {
		shutdown = true;
	}
}
