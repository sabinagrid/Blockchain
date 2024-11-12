package guru.springframework.json;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Blockchain {
    private final List<Block> chain;
    private final List<Message> pendingData;
    private final ReentrantLock lock = new ReentrantLock();

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingData = new ArrayList<>();
        long timestamp = new Date().getTime();
        String hash = StringUtil.applySha256("1" + timestamp + "0");
        Block firstBlock = new Block("0", 0, new ArrayList<>(), hash);
        chain.add(firstBlock);
    }

    public void addNewMessage(Message message) {
        synchronized (lock) {
            pendingData.add(message);
            lock.notifyAll();
        }
    }

    public void addNewBlock(Block block) {
        synchronized (lock) {
            if (validateNewBlock(block, chain.get(chain.size() - 1))) {
                chain.add(block);
                lock.notifyAll();
            }
        }
    }

    private boolean validateNewBlock(Block block, Block previousBlock) {
        return block.getPreviousHash().equals(previousBlock.getHash()) &&
                block.getHash().startsWith("0");
    }

    public boolean shouldContinueMining() {
        return chain.size() < 15;
    }

    public int getNextId() {
        return chain.size() + 1;
    }

    public String getLastHash() {
        return chain.get(chain.size() - 1).getHash();
    }

    public List<Message> getPendingData() {
        return new ArrayList<>(pendingData);
    }

    public void displayBlockchain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }
}
