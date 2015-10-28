package encryptor1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class DoubleEncryptionTest {

    EncryptionAlgorithm EA = new EncryptionAlgorithm() {

        public int getKeyStrengh() {
            return 10;
        }

        public String encryptWithKey(String data, int key) throws IOException {
            return null;
        }

        public ArrayList<String> encrypt(String data) throws IOException {
            ArrayList<String> returnedValues = new ArrayList<String>();
            if (data.equals("abc")) {
                returnedValues.add("1");
                returnedValues.add("bcd");
                return returnedValues;
            } else {
                returnedValues.add("2");
                returnedValues.add("def");
                return returnedValues;
            }
        }

        public String decrypt(String data, String keyString)
                throws InvalidEncryptionKeyException {
            if (data.equals("%k9B") && keyString.equals("4")) {
                return "O \01";
            } else {
                return "yes";
            }
        }
    };

    @Test
    public void testEncrypt() throws IOException {
        String data = "abc";
        String expectedKey = "1\n2";
        String expected = "def";
        ArrayList<String> actual = new DoubleEncryption(EA).encrypt(data);
        assertEquals(expectedKey, actual.get(0));
        assertEquals(expected, actual.get(1));
    }

    @Test
    public void testEncryptWithKey() throws IOException {
        String data = (char) 19 + "abc";
        String expected = "def";
        String actual = new DoubleEncryption(EA).encryptWithKey(data, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecrypt() throws InvalidEncryptionKeyException {
        String data = "%k9B";
        String expected = "yes";
        String key = "4\n10";
        String actual = new DoubleEncryption(EA).decrypt(data, key);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKeyStrengh() {
        assertEquals(10, new DoubleEncryption(EA).getKeyStrengh());
    }
    
    @Test(expected = Exceptions.InvalidEncryptionKeyException.class)
    public void testDecryptWrongKey() throws InvalidEncryptionKeyException {
        new DoubleEncryption(EA).decrypt("error", "2\n\n");
    }
}
