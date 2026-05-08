package task4;

import java.io.IOException;

public class LocalClient{
	public static void main(String[] s) throws IOException, InterruptedException {
		// Server
		ServerRequestHandler serverHandle = new ServerRequestHandler(8001);
		Thread t = new Thread(serverHandle);
		t.start();
		
		//  Client
		String testString = "DSE";
		ClientProxy p =  new ClientProxy();
		System.out.println(p.hello(testString));
		System.out.println(p.goodbye(testString));
		System.out.println(p.insult(testString));
		
		serverHandle.shutdownServer();
		t.join();
	}
}
