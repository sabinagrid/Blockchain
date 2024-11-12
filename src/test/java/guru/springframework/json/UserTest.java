package guru.springframework.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private Blockchain blockchain;
    private User user;

    @BeforeEach
    public void setUp() {
        blockchain = new Blockchain();
        user = new User(blockchain, "TestUser");
    }

    @Test
    public void whenSignMessage_thenReturnsSignedString() {
        String signedMessage = user.signMessage();
        assertNotNull(signedMessage);
        assertFalse(signedMessage.isEmpty());
    }

    @Test
    public void whenUserAddsMessage_thenMessageIsAddedToBlockchain() {
        user.run();
        assertTrue(blockchain.getPendingData().size() > 0);
    }
}
