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

import encryptor1.Exceptions.InvalidEncryptionKeyException;

@RunWith(Parameterized.class)
public class EncryptionAlgorithmGetKeyStrengh<T> {

	@Parameter
	public IEncryptionAlgorithm<T> EA;
	@Parameter(value = 1)
	public int expected;

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { new ShiftUpEncryption(), 3 },
				{ new XorEncryption(), 3 },
				{ new ShiftMultiplyEncryption(), 3 },
				{ new IEncryptionAlgorithm<Integer>() {

					public int getKeyStrengh() {
						return 100;
					}

					public String encrypt(String data, Integer key)
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
