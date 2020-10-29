import ClientServerApp.SendReadMsg;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class TestTCPgradle {

    private final int port = 6666;
    private final String host = "127.0.0.1";
    private Socket clientSocket;
    private final SendReadMsg sendReadMsg = new SendReadMsg();
    private final Logger log = LoggerFactory.getLogger(TestTCPgradle.class);

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
    @Tag("TestServerSocket")
    public void streamSocket(String message) throws IOException {
        if (message.isEmpty()) {
            log.error("Message is Empty", new RuntimeException("Message is Empty"));
            throw new RuntimeException("Message is Empty");
        }
        sendReadMsg.sendMessage(clientSocket, message);
        String readMessage = sendReadMsg.readMessage(clientSocket);
        if (readMessage.contains(message)) {
            log.info(readMessage);
        } else {
            log.error(readMessage, new RuntimeException("Error message"));
            throw new RuntimeException("Error message");
        }
    }

    @AfterEach
    public void afterEach() throws IOException {
        clientSocket.close();
    }
}
