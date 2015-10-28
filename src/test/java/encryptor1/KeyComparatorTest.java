package encryptor1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class KeyComparatorTest {

    KeyComparator keyCompare = new KeyComparator();
    EncryptionAlgorithm algorithm = new EncryptionAlgorithm() {

        public int getKeyStrengh() {
            return 0;
        }

        public String encryptWithKey(String data, int key) throws IOException {
            return null;
        }

        public ArrayList<String> encrypt(String data) throws IOException {
            return null;
        }

        public String decrypt(String data, String keyString)
                throws InvalidEncryptionKeyException {
            return null;
        }
    };
    EncryptionAlgorithm algorithm1;
    EncryptionAlgorithm algorithm2;
    EncryptionAlgorithm algorithm3;
    EncryptionAlgorithm algorithm4;

    @Before
    public void setClasses() {
        algorithm1 = Mockito.spy(algorithm);
        algorithm2 = Mockito.spy(algorithm);
        algorithm3 = Mockito.spy(algorithm);
        algorithm4 = Mockito.spy(algorithm);
        Mockito.when(algorithm1.getKeyStrengh()).thenReturn(3);
        Mockito.when(algorithm2.getKeyStrengh()).thenReturn(2);
        Mockito.when(algorithm3.getKeyStrengh()).thenReturn(1);
        Mockito.when(algorithm4.getKeyStrengh()).thenReturn(2);
    }

    @Test
    public void testComparePositive() {
        int result = keyCompare.compare(algorithm1, algorithm2);
        assertTrue(result > 0);
    }

    @Test
    public void testCompareNegative() {
        int result = keyCompare.compare(algorithm3, algorithm4);
        assertTrue(result < 0);
    }

    @Test
    public void testCompareZero() {
        int result = keyCompare.compare(algorithm2, algorithm4);
        assertTrue(result == 0);
    }

}
