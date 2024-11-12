package guru.springframework.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @Test
    public void whenApplySha256_thenReturnsValidHash() {
        String hash = StringUtil.applySha256("test");
        assertNotNull(hash);
        assertEquals(64, hash.length());
    }

    @Test
    public void whenApplySha256WithEmptyString_thenReturnsValidHash() {
        String hash = StringUtil.applySha256("");
        assertNotNull(hash);
        assertEquals(64, hash.length());
    }

    @Test
    public void whenApplySha256WithNull_thenThrowsException() {
        assertThrows(RuntimeException.class, () -> StringUtil.applySha256(null));
    }

    @Test
    void whenApplySha256WithEmptyString_thenHashIsGenerated() {
        String hash = StringUtil.applySha256("");
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }
}
