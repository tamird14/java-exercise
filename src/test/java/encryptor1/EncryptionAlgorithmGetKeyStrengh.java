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

import encryptor1.Exceptions.InvalidEncryptionKeyException;

@RunWith(Parameterized.class)
public class EncryptionAlgorithmGetKeyStrengh {

    @Parameter
    public EncryptionAlgorithm EA;
    @Parameter(value = 1)
    public int expected;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { new ShiftUpEncryption(), 3 },
                { new XorEncryption(), 3 },
                { new ShiftMultiplyEncryption(), 3 },
                { new EncryptionAlgorithm() {

                    public int getKeyStrengh() {
                        return 100;
                    }

                    public String encryptWithKey(String data, int key)
                            throws IOException {
                        return null;
                    }

                    public ArrayList<String> encrypt(String data)
                            throws IOException {
                        return null;
                    }

                    public String decrypt(String data, String keyString)
                            throws InvalidEncryptionKeyException {
                        return null;
                    }
                }, 100 } };
        return Arrays.asList(data);
    }

    @Test
    public void testGetKeyStrengh() {
        int actual = EA.getKeyStrengh();
        assertEquals(expected, actual);
    }

}
