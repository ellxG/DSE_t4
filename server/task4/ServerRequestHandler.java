package task4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandler implements Runnable{
	Invoker invoker;
	ServerSocket server;
	int port;
	Boolean keepSocket;
	public ServerRequestHandler(int port) {
		invoker = new Invoker();
		this.port = port;
		keepSocket = true;
	};

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server running on port " + port);
			Socket socket = server.accept();
			InputStream sin = socket.getInputStream();
			DataInputStream din = new DataInputStream(sin);
			OutputStream sout = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(sout);
			while(keepSocket) {
				
				
				int length = din.readInt();
	            byte[] request = new byte[length];
	            din.readFully(request);
	      
				byte[] response = invoker.handleRequest(request);
				
				
				dout.writeInt(response.length);
	            dout.write(response);
	            dout.flush();
			}
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
