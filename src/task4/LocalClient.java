package task4;

import java.io.IOException;

public class LocalClient{
	public static void main(String[] s) throws IOException, InterruptedException {
		ServerRequestHandler serverHandle = new ServerRequestHandler(8000);
		Thread t = new Thread(serverHandle);
		t.start();
		
		String testString = "DSE";
		ClientProxy p =  new ClientProxy();
		System.out.println(p.hello(testString));
		System.out.println(p.goodbye(testString));
		
		t.join();
	}
}
