package task4;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class Requestor {
	Marshaller marshaller;
	ClientRequestHandler handler;
	public Requestor() {
		marshaller = new Marshaller();
		handler = new ClientRequestHandler(8001);
	}
	
	public Message request(Message m) throws RemoteException, UnsupportedOperationException{
		byte[] requestData = marshaller.marshall(m);
		byte[] responseData;
		try {
			responseData = handler.remoteRequest(requestData);
		} catch (UnknownHostException e) {
			throw new RemoteException(e.getMessage());
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
		Message response = marshaller.unmarshall(responseData);
		if (response.RequestType().equals("NotSupported")) {
			throw new UnsupportedOperationException("Method " + m.RequestType() + " is not supported");
		}
		return response;
	}
}
