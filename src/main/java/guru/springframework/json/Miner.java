package guru.springframework.json;

import java.util.Date;

public class Miner extends Thread {
    private final Blockchain blockchain;
    private final int minerId;

    public Miner(Blockchain blockchain, int minerId) {
        this.blockchain = blockchain;
        this.minerId = minerId;
    }

    public void run() {
        while (blockchain.shouldContinueMining()) {
            Block block = mineBlock();
            blockchain.addNewBlock(block);
        }
    }

    private Block mineBlock() {
        String previousHash = blockchain.getLastHash();
        int blockId = blockchain.getNextId();
        long timestamp = new Date().getTime();
        int magicNumber = 0;
        String hash;
        do {
            magicNumber++;
            hash = StringUtil.applySha256(blockId + timestamp + magicNumber + previousHash);
        } while (!hash.startsWith("0"));

        return new Block(previousHash, minerId, blockchain.getPendingData(), hash);
    }
}
