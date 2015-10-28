package encryptor1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class EncryptionAlgorithmEncryptTest {

    @Parameter
    public Encryption EA;
    @Parameter(value = 1)
    public String expected;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new ShiftUpEncryption(), (char) 29 + "\n.E" },
                { new XorEncryption(), (char) 25 + "\n.1" },
                { new ShiftMultiplyEncryption(), "{\0" + (char) 4 + "c" } };
        return Arrays.asList(data);
    }

    @Test
    public void testEncrypt() throws IOException {
        String data = (char) 19 + "\0$;";
        int key = 10;
        String expectedKey = Integer.toString(key);
        Encryption test = Mockito.spy(EA);
        Mockito.when(test.generateKey()).thenReturn(key);
        ArrayList<String> actual = test.encrypt(data);
        assertEquals(expectedKey, actual.get(0));
        assertEquals(expected, actual.get(1));
    }

}
