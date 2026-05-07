package task4;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Marshaller {
	private final Charset charset;
	private static final int intByteSize = 4;
	public Marshaller() {
		charset = StandardCharsets.UTF_16;
	}
	
	// This was an annoying bug :(
	private byte[] stringToByte(String s) {
        ByteBuffer buf = charset.encode(s);
        byte[] bytes = new byte[buf.remaining()];
        buf.get(bytes);
        return bytes;
    }
	
	private String byteToString(byte[] data) {
		return charset.decode(ByteBuffer.wrap(data)).toString();
	}

	public byte[] marshall(Message m) {
		byte[] encodedRequestType = stringToByte(m.RequestType());
		byte[] encodedBody = stringToByte(m.Body());
		
		int requestArrayLength = encodedRequestType.length;
		int bodyArrayLength = encodedBody.length;
		
		ByteBuffer buf = ByteBuffer.allocate(intByteSize + requestArrayLength +
											 intByteSize + bodyArrayLength);
		
		buf.putInt(requestArrayLength);
		buf.put(encodedRequestType);
		buf.putInt(bodyArrayLength);
		buf.put(encodedBody);
		buf.flip();
		return buf.array();
	}
	
	public Message unmarshall(byte[] data) {
		ByteBuffer dataBuffer = ByteBuffer.wrap(data);
		
		int requestArrayLength = dataBuffer.getInt();
		byte[] encodedRequestType = new byte[requestArrayLength];
		dataBuffer.get(encodedRequestType);
		
		int bodyArrayLeng = dataBuffer.getInt();
		byte[] encodedBody = new byte[bodyArrayLeng];
		dataBuffer.get(encodedBody);
		return new Message(byteToString(encodedRequestType), byteToString(encodedBody));
	}
}
