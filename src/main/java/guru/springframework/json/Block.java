package guru.springframework.json;

import java.util.List;

public class Block {
    private final String hash;
    private final String previousHash;
    private final int minerId;

    public Block(String previousHash, int minerId, List<Message> data, String hash) {
        this.previousHash = previousHash;
        this.minerId = minerId;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String toString() {
        return "Block:\nCreated by miner" + minerId + "\nHash: " + hash;
    }
}
