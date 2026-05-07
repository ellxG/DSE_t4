package task4;

import java.rmi.RemoteException;

public class ClientProxy implements Greetings{
	Requestor r;
	public ClientProxy() {
		r = new Requestor();
	}
	
	public String hello(String name){
		Message response = null;
		try {
			response = r.request(new Message("hello", name));
		} catch (RemoteException e) {
			e.printStackTrace();
			return "Error: Remote Connection failed.";
		} catch (UnsupportedOperationException e) {
			return "Error: "  + e.getMessage();
		}
		return response.Body();
	}
	public String goodbye(String name){
		Message response = null;
		try {
			response = r.request(new Message("goodbye", name));
		} catch (RemoteException e) {
			e.printStackTrace();
			return "Error: Remote Connection failed.";
		} catch (UnsupportedOperationException e) {
			return "Error: "  + e.getMessage();
		}
		return response.Body();
	}
	
	public String insult(String name) {
		Message response = null;
		try {
			response = r.request(new Message("insult", name));
		} catch (RemoteException e) {
			e.printStackTrace();
			return "Error: Remote Connection failed.";
		} catch (UnsupportedOperationException e) {
			return "Error: "  + e.getMessage();
		}
		return response.Body();
	}
}
