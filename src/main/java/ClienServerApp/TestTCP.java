package ClienServerApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.Socket;

public class TestTCP {

    private final int port = 6666;
    private final String host = "127.0.0.1";
    private Socket clientSocket;
    private final SendReadMsg sendReadMsg = new SendReadMsg();

    @BeforeEach
    public void beforeEach() throws IOException {
        clientSocket = new Socket(host, port);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Privet, kak dela u tebya?",
            "0",
            "",
            " ",
            "0123456789",
            "!@#$%^&*()\"_-+='/.,?№;:",
            "Привет",
            "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n" +
                    "111111111111111111111111111111111111111111111111111111111111111111\n"
    })
    @DisplayName("StreamSocket")
    public void streamSocket(String message) throws IOException {
        if (message.isEmpty()) {
            throw new RuntimeException("Message is Empty");
        }
        sendReadMsg.sendMessage(clientSocket, message);
        String readMessage = sendReadMsg.readMessage(clientSocket);
        if (readMessage.contains(message)) {
            System.out.println(readMessage);
        } else {
            throw new RuntimeException("Error message\n" + readMessage);
        }
    }

    @AfterEach
    public void afterEach() throws IOException {
        clientSocket.close();
    }
}
