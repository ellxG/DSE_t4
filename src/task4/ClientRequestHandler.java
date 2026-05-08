package task4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRequestHandler {
	private int port;
	public ClientRequestHandler(int port) {
		this.port = port;
	}
	public byte[] remoteRequest(byte[] request) throws UnknownHostException, IOException {
		
		try (Socket socket = new Socket("localhost", port)){
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeInt(request.length);
            dout.write(request);
			dout.flush();
			
			DataInputStream din = new DataInputStream(socket.getInputStream());

			int length = din.readInt();
			byte[] response = new byte[length];
            din.readFully(response);
			return response;
		}
	}
}
