package guru.springframework.json;

import java.security.*;
import java.util.Base64;

public class User extends Thread {
    private final Blockchain blockchain;
    private final String userName;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public User(Blockchain blockchain, String userName) {
        this.blockchain = blockchain;
        this.userName = userName;

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            KeyPair pair = keyGen.generateKeyPair();
            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String message = userName + " message " + i;
            String signedMessage = signMessage(message);
            blockchain.addNewMessage(new Message(signedMessage, publicKey, message));
        }
    }

    private String signMessage(String message) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
