package task4;

import java.rmi.RemoteException;

public class ClientProxy implements Greetings{
	Requestor r;
	public ClientProxy() {
		r = new Requestor();
	}
	
	private String remoteRequest(String methodName, String messageBody) {
		Message response;
		try {
			response = r.request(new Message(methodName, messageBody));
		} catch (RemoteException e) {
			e.printStackTrace();
			return "Error: Remote Connection failed.";
		} catch (UnsupportedOperationException e) {
			return "Error: "  + e.getMessage();
		}
		return response.Body();
	}
	
	public String hello(String name){
		return remoteRequest("hello", name);
	}
	
	public String goodbye(String name){
		return remoteRequest("goodbye", name);
	}
	
	public String insult(String name) {
		return remoteRequest("insult", name);
	}
}
