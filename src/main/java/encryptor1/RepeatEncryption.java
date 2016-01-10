package encryptor1;

import java.io.IOException;
import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class RepeatEncryption<T> implements IEncryptionAlgorithm<T> {
	int numberOfRepetitions;
	IEncryptionAlgorithm<T> EA;

	public RepeatEncryption(int numberOfRepetitions,
			IEncryptionAlgorithm<T> EA) {
		this.numberOfRepetitions = numberOfRepetitions;
		this.EA = EA;
	}

	// public ArrayList<String> encrypt(String data, boolean reset)
	// throws IOException {
	// ArrayList<String> dataKey = this.EA.encrypt(data, true);
	// String encryptedData = "";
	// for (int i = 0; i < numberOfRepetitions - 1; i++) {
	// encryptedData = dataKey.get(1);
	// dataKey = this.EA.encrypt(encryptedData, false);
	// }
	// return dataKey;
	// }

	public String encrypt(String data, T key) throws IOException {
		String encryptedData = data;
		for (int i = 0; i < numberOfRepetitions; i++) {
			encryptedData = this.EA.encrypt(encryptedData, key);
		}
		return encryptedData;
	}

	public String decrypt(String data, String keyString)
			throws InvalidEncryptionKeyException {
		String encryptedData = data;
		for (int i = 0; i < numberOfRepetitions; i++) {
			encryptedData = this.EA.decrypt(encryptedData, keyString);
		}
		return encryptedData;
	}

	public int getKeyStrengh() {
		return this.EA.getKeyStrengh();
	}

}
