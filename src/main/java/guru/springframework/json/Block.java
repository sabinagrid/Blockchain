package guru.springframework.json;

import java.util.List;

public class Block {
    private final String hash;
    private final String previousHash;
    private final int minerId;
    private final List<Message> data;

    public Block(String previousHash, int minerId, List<Message> data, String hash) {
        this.previousHash = previousHash;
        this.minerId = minerId;
        this.hash = hash;
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public String toString() {
        StringBuilder blockInfo = new StringBuilder();
        blockInfo.append("Block:\n")
                .append("Created by miner").append(minerId).append("\n")
                .append("Hash: ").append(hash).append("\n")
                .append("Previous Hash: ").append(previousHash).append("\n");

        if (data != null && !data.isEmpty()) {
            blockInfo.append("Block data:\n");
            for (Message message : data) {
                blockInfo.append(message.getMessage()).append("\n");
            }
        } else {
            blockInfo.append("No transactions\n");
        }
        return blockInfo.toString();
    }
}
