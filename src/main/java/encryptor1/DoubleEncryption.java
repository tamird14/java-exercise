/**
 * 
 */
package encryptor1;

import java.io.IOException;
import java.util.ArrayList;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

/**
 * @author Tamir
 *
 */
public class DoubleEncryption implements EncryptionAlgorithm {
    EncryptionAlgorithm EA;

    /**
     * 
     */
    public DoubleEncryption(EncryptionAlgorithm EA) {
        this.EA = EA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#encrypt(java.lang.String)
     */
    public ArrayList<String> encrypt(String data) throws IOException {
        ArrayList<String> encryptedData = this.EA.encrypt(data);
        String key1 = encryptedData.get(0);
        encryptedData = this.EA.encrypt(encryptedData.get(1));
        String key = key1 + "\n" + encryptedData.get(0);
        encryptedData.set(0, key);
        return encryptedData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#encryptWithKey(java.lang.String, int)
     */
    public String encryptWithKey(String data, int key) throws IOException {
        return encrypt(data).get(1);
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

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#getKeyStrengh()
     */
    public int getKeyStrengh() {
        return this.EA.getKeyStrengh();
    }

}
