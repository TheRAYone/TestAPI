package ClientServerApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendReadMsg {
    private final byte[] readMsg = new byte[256];
    private final Logger log = LoggerFactory.getLogger(SendReadMsg.class);

    public void sendMessage(Socket socket, String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(message.getBytes());
        log.info("Send message: " + message);
    }

    public String readMessage(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        inputStream.read(readMsg);
        return new String(readMsg);
    }
}
