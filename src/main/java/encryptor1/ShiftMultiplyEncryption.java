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
public class ShiftMultiplyEncryption extends IntegerEncryption {

	public ShiftMultiplyEncryption() {
		super();
	}

	public ShiftMultiplyEncryption(int key) {
		super(key);
	}

	// public ArrayList<String> encrypt(String data, boolean reset) throws
	// IOException {
	// if (reset) {
	// if (randomKey)
	// act = new EncryptMultiplyAction(data);
	// else
	// act = new EncryptMultiplyAction(data, key);
	// } else {
	// act.changeData(data);
	// }
	// String encryptedData = act.performAction();
	// ArrayList<String> keyData = new ArrayList<String>();
	// keyData.add(Integer.toString(act.getKey()));
	// keyData.add(encryptedData);
	// return keyData;
	// }

	public String encrypt(String data, Integer key) throws IOException {
		act = new EncryptMultiplyAction(data);
		String encryptedData = act.performAction();
		return encryptedData;
	}

	public String decrypt(String data, String keyString)
			throws InvalidEncryptionKeyException {
		int key = 0;
		try {
			key = Integer.parseInt(keyString);
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyException();
		}
		this.act = new DecryptMultiplyAction(data, key);
		String decryptedData = act.performAction();
		return decryptedData;
	}

}
