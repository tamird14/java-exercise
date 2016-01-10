/**
 * 
 */
package encryptor1;

/**
 * @author Tamir
 *
 */
public abstract class IntegerEncryption
		implements IEncryptionAlgorithm<Integer> {
	protected Action act = null;
	protected int key;
	protected boolean randomKey = true;

	public IntegerEncryption() {
		this.randomKey = true;
	}

	public IntegerEncryption(int key) {
		this.key = key;
		this.randomKey = false;
	}

	public int getKeyStrengh() {
		return 3;
	}

}
