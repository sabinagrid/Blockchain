package guru.springframework.json;

import java.security.PublicKey;

public record Message(String signedMessage, PublicKey publicKey, String message) {
}
