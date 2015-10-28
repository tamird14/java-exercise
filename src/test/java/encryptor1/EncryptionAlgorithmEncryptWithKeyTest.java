package encryptor1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EncryptionAlgorithmEncryptWithKeyTest {

    @Parameter
    public EncryptionAlgorithm EA;
    @Parameter(value = 1)
    public String expected;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new ShiftUpEncryption(), (char) 29 + "\n.E" },
                { new XorEncryption(), (char) 25 + "\n.1" },
                { new ShiftMultiplyEncryption(), "{\0" + (char) 4 + "c" }, };
        return Arrays.asList(data);
    }

    @Test
    public void testEncryptWithKey() throws IOException {
        String data = (char) 19 + "\0$;";
        int key = 10;
        String actual = EA.encryptWithKey(data, key);
        assertEquals(expected, actual);
    }
}
