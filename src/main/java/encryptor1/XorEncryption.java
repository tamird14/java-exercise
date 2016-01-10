package encryptor1;

import java.io.IOException;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class XorEncryption extends IntegerEncryption {

	public XorEncryption() {
		super();
	}

	public XorEncryption(int key) {
		super(key);
	}

	public String encrypt(String data, Integer key) throws IOException {
		act = new EncryptUpAction(data, key);
		String encryptedData = act.performAction();
		return encryptedData;
	}

	public String decrypt(String data, String keyString)
			throws InvalidEncryptionKeyException {
		try {
			int key = Integer.parseInt(keyString);
			act = new XorAction(data, key);
			String decryptedData = act.performAction();
			return decryptedData;
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyException();
		}
	}

}
