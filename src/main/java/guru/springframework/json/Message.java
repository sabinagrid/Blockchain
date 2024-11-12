package guru.springframework.json;

import java.security.PublicKey;

public class Message {
    private final String signedMessage;
    private final PublicKey publicKey;
    private final String message;

    public Message(String signedMessage, PublicKey publicKey, String message) {
        this.signedMessage = signedMessage;
        this.publicKey = publicKey;
        this.message = message;
    }

    public String getSignedMessage() {
        return signedMessage;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getMessage() {
        return message;
    }
}
