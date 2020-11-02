import ClientServerApp.SendReadMsg;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class TestTCPgradle {

    private final int port = 6666;
    private final String host = "127.0.0.1";
    private static  Socket clientSocket;
    private final SendReadMsg sendReadMsg = new SendReadMsg();
    private final Logger log = LoggerFactory.getLogger(TestTCPgradle.class);

    @BeforeEach
    void beforeEach() throws IOException {
        clientSocket = new Socket(host, port);
    }
    @Ignore
    @ParameterizedTest(name = "Test streamSocket testString: ( {0} )")
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
    @Tag("UnitTest")
    void streamSocket(String message) throws IOException {
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

    @Test
    @Tag("IntegrationTest")
    void test2IntegrationTest(){
        log.info("Test 2 - IntegrationTest");
    }

    @Test
    @Tag("IntegrationTest")
    void test3IntegrationTest(){
        log.info("Test 3 - IntegrationTest");
    }

    @Test
    @Tag("UnitTest")
    void test4IntegrationTest(){
        log.info("Test 4 - UnitTest");
    }

    @AfterEach
    void afterEach() throws IOException {
        clientSocket.close();
    }
}
