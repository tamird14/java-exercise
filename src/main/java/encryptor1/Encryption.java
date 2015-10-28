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
public abstract class Encryption implements EncryptionAlgorithm {

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#encrypt(java.lang.String)
     */
    public ArrayList<String> encrypt(String data) throws IOException {
        int key = generateKey();
        ArrayList<String> keyData = new ArrayList<String>();
        String encryptedData = encryptWithKey(data, key);
        keyData.add(Integer.toString(key));
        keyData.add(encryptedData);
        return keyData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#encryptWithKey(java.lang.String, int)
     */
    public String encryptWithKey(String data, int key) throws IOException {
        String encryptedData = addKey(data, key);
        return encryptedData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#decrypt(java.lang.String,
     * java.lang.String)
     */
    public String decrypt(String data, String keyString)
            throws InvalidEncryptionKeyException {
        try {
            int key = Integer.parseInt(keyString);
            String decryptedData = removeKey(data, key);
            return decryptedData;
        } catch (NumberFormatException e) {
            throw new InvalidEncryptionKeyException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.EncryptionAlgorithm#getKeyStrengh()
     */
    public int getKeyStrengh() {
        return 3;
    }

    /**
     * @return
     */
    protected abstract int generateKey();

    /**
     * @param data
     * @param key
     * @return
     */
    protected abstract String addKey(String data, int key);

    /**
     * @param data
     * @param key
     * @return
     */
    protected abstract String removeKey(String data, int key);

}
