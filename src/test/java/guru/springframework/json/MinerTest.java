package guru.springframework.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinerTest {
    private Blockchain blockchain;
    private Miner miner;

    @BeforeEach
    public void setUp() {
        blockchain = new Blockchain();
        miner = new Miner(blockchain, 1);
    }

    @Test
    public void whenMineBlock_thenBlockHasValidHash() {
        Block block = miner.mineBlock();
        assertTrue(block.getHash().startsWith("0"));
        assertEquals(blockchain.getLastHash(), block.getPreviousHash());
    }

    @Test
    public void whenRunMiner_thenBlockAddedToBlockchain() {
        miner.run();
        assertTrue(blockchain.getChain().size() > 1);
    }
}
