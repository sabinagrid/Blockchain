package guru.springframework.json;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void whenCreateBlock_thenBlockFieldsAreSetCorrectly() {
        Block block = new Block("previousHash", 1, List.of(), "hashValue");
        assertEquals("previousHash", block.getPreviousHash());
        assertEquals("hashValue", block.getHash());
    }

    @Test
    public void whenToStringCalled_thenReturnsFormattedString() {
        Block block = new Block("previousHash", 1, List.of(), "hashValue");
        String blockString = block.toString();
        assertTrue(blockString.contains("Created by miner1"));
        assertTrue(blockString.contains("Hash: hashValue"));
    }
}
