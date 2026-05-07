package task4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRequestHandler {
	public ClientRequestHandler() {
	}
	public byte[] remoteRequest(byte[] request) throws UnknownHostException, IOException {
		
		try (Socket socket = new Socket("localhost", 8000)){
			OutputStream sout = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(sout);
			dout.writeInt(request.length);
            dout.write(request);
			dout.flush();
			
			InputStream sin = socket.getInputStream();
			DataInputStream din = new DataInputStream(sin);
			
			int length = din.readInt();
            byte[] response = new byte[length];
            din.readFully(response);
			return response;
		}
	}
}
