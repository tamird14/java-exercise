package encryptor1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class RepeatEncryptionTest {

	EncryptionAlgorithm EA = new EncryptionAlgorithm() {

		public int getKeyStrengh() {
			return 42;
		}

		public String encryptWithKey(String data, int key) throws IOException {
			String encryptedData = "";
            if (data.equals("a")) {
            	encryptedData = "b";
            } else if (data.equals("b")) {
            	encryptedData = "c";
            } else if (data.equals("c")) {
            	encryptedData = "d";
            } else {
            	encryptedData = "e";
            }
            return encryptedData;
		}

		public ArrayList<String> encrypt(String data) throws IOException {
			ArrayList<String> returnedValues = new ArrayList<String>();
			returnedValues.add("1");
			returnedValues.add("b");
			return returnedValues;
		}

		public String decrypt(String data, String keyString)
				throws InvalidEncryptionKeyException {
			String encryptedData = "";
			if (data.equals("b")) {
				encryptedData = "c";
			} else {
				encryptedData = "d";
			}
			return encryptedData;
		}
	};

	@Test
	public void testEncrypt() throws IOException {
		String data = "a";
		String expectedKey = "1";
		String expected = "e";
		ArrayList<String> actual = new RepeatEncryption(4, EA).encrypt(data);
		assertEquals(expectedKey, actual.get(0));
		assertEquals(expected, actual.get(1));
	}

	@Test
	public void testDecrypt() throws InvalidEncryptionKeyException {
		String data = "b";
		String key = "1";
		String expected = "d";
		String actual = new RepeatEncryption(2, EA).decrypt(data, key);
		assertEquals(expected, actual);
	}

	@Test
	public void testEncryptWithKey() throws IOException {
		String data = "a";
		int key = 1;
		String expected = "d";
		String actual = new RepeatEncryption(3, EA).encryptWithKey(data, key);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetKeyStrengh() {
		assertEquals(42, new RepeatEncryption(10, EA).getKeyStrengh());
	}

}
