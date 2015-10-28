package encryptor1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

@RunWith(Parameterized.class)
public class EncryptionAlgorithmDecryptTest {

    @Parameter
    public EncryptionAlgorithm EA;
    @Parameter(value = 1)
    public String expected;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { new ShiftUpEncryption(), "I6Zq" },
                { new XorEncryption(), "YJnq" },
                { new ShiftMultiplyEncryption(),
                        (char) 171 + "@" + (char) 132 + "" + (char) 19 } };
        return Arrays.asList(data);
    }

    @Test
    public void testDecrypt() throws InvalidEncryptionKeyException {
        String data = "S@d{";
        String key = "10";
        String actual = EA.decrypt(data, key);
        assertEquals(expected, actual);
    }
    
    @Test(expected = Exceptions.InvalidEncryptionKeyException.class)
    public void testDecryptWrongKey() throws InvalidEncryptionKeyException{
        String data = "ERROR";
        String key = "five";
        EA.decrypt(data, key);
    }

}
