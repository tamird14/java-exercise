/**
 * 
 */
package encryptor1;

import java.io.IOException;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

/**
 * @author Tamir
 *
 */
public interface IEncryptionAlgorithm<T> {

	public String encrypt(String data, T key) throws IOException;

	public String decrypt(String data, String keyString)
			throws InvalidEncryptionKeyException;

	public int getKeyStrengh();

}