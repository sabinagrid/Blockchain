package guru.springframework.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockchainTest {

    private Blockchain blockchain;

    @BeforeEach
    public void setUp() {
        blockchain = new Blockchain();
    }

    @Test
    public void whenBlockchainInitialized_thenFirstBlockIsAdded() {
        assertTrue(blockchain.isBlockchainInitialized(), "Blockchain should be initialized with a genesis block");
        assertEquals(1, blockchain.getChainSize(), "Blockchain should contain exactly one block initially");
    }

    @Test
    public void whenAddNewBlockWithValidPreviousHash_thenBlockAddedToChain() {
        Block newBlock = mineBlock(blockchain);

        blockchain.addNewBlock(newBlock);

        assertEquals(2, blockchain.getChainSize(), "Blockchain should contain two blocks after adding a valid block");
    }

    @Test
    public void whenAddInvalidBlock_thenBlockIsNotAdded() {
        Blockchain blockchain = new Blockchain();
        Block invalidBlock = new Block("invalidHash", 1, new ArrayList<>(), "someInvalidHash");

        blockchain.addNewBlock(invalidBlock);

        assertEquals(1, blockchain.getChainSize(), "Blockchain should not accept invalid blocks");
    }

    @Test
    void whenAddNewBlockWithInvalidPreviousHash_thenBlockNotAdded() {
        Blockchain blockchain = new Blockchain();
        Block invalidBlock = new Block("invalidHash", 1, new ArrayList<>(), "fakeHash");

        blockchain.addNewBlock(invalidBlock);

        assertEquals(1, blockchain.getChainSize(), "Blockchain should not add an invalid block");
    }

    private Block mineBlock(Blockchain blockchain) {
        String previousHash = blockchain.getLastHash();
        int blockId = blockchain.getNextId();
        long timestamp = System.currentTimeMillis();
        int magicNumber = 0;
        String hash;

        do {
            magicNumber++;
            hash = StringUtil.applySha256(blockId + timestamp + magicNumber + previousHash);
        } while (!hash.startsWith("0"));

        List<Message> data = new ArrayList<>();
        return new Block(previousHash, 1, data, hash);
    }
}
