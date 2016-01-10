/**
 * 
 */
package encryptor1;

import java.io.IOException;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

/**
 * @author Tamir
 * @param <T>
 *
 */
public class DoubleEncryption<T> implements IEncryptionAlgorithm<DoubleKey<T>> {
	IEncryptionAlgorithm<T> EA;

	/**
	 * 
	 */
	public DoubleEncryption(IEncryptionAlgorithm<T> EA) {
		this.EA = EA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encryptor1.EncryptionAlgorithm#encrypt(java.lang.String)
	 */
	// public ArrayList<String> encrypt(String data, boolean reset)
	// throws IOException {
	// ArrayList<String> encryptedData = this.EA.encrypt(data, true);
	// String key1 = encryptedData.get(0);
	// encryptedData = this.EA.encrypt(encryptedData.get(1), true);
	// String key = key1 + "\n" + encryptedData.get(0);
	// encryptedData.set(0, key);
	// return encryptedData;
	// }

	public String encrypt(String data, DoubleKey<T> key) throws IOException {
		String encryptedData = this.EA.encrypt(data, key.getKey1());
		encryptedData = this.EA.encrypt(encryptedData, key.getKey2());
		return encryptedData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encryptor1.EncryptionAlgorithm#decrypt(java.lang.String,
	 * java.lang.String)
	 */
	public String decrypt(String data, String key)
			throws InvalidEncryptionKeyException {
		String[] keys = key.split("\n");
		if (keys.length != 2) {
			throw new InvalidEncryptionKeyException();
		}
		String decryptedData = this.EA.decrypt(data, keys[1]);
		decryptedData = this.EA.decrypt(decryptedData, keys[0]);
		return decryptedData;
	}

	public int getKeyStrengh() {
		return this.EA.getKeyStrengh();
	}

}
