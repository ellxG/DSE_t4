package task4;

public class Invoker {
	RemoteObject ro;
	Marshaller marshaller;
	
	public Invoker() {
		ro = new RemoteObject();
		marshaller = new Marshaller();
	}
	
	public byte[] handleRequest(byte[] request) {
		Message m = marshaller.unmarshall(request);
		Message response;
		if (m.RequestType().equals("hello")) {
			response = new Message("hello", ro.hello(m.Body()));
		}
		else if (m.RequestType().equals("goodbye"))  {
			response = new Message("goodbye", ro.goodbye(m.Body()));
		}
		else {
			response = new Message("NotSupported", m.RequestType());
		}
		return marshaller.marshall(response);
	}
}
