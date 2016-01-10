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
public interface EncryptionAlgorithm {

    public ArrayList<String> encrypt(String data, boolean reset) throws IOException;

    public String decrypt(String data, String keyString)
            throws InvalidEncryptionKeyException;

    public int getKeyStrengh();

}