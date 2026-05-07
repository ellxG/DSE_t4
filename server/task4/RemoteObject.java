package task4;

public class RemoteObject implements Greetings{
	public RemoteObject() {};
	
	public String hello(String name) {
		return "Hello, " + name + "!";
 	}
	
	public String goodbye(String name) {
		return name + ", unfortunately we have to part ways here.";
	}
}