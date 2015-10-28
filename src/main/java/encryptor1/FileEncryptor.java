/**
 * 
 */
package encryptor1;

import java.io.IOException;
import java.util.ArrayList;

import encryptor1.Exceptions.InvalidEncryptionKeyException;
import encryptor1.Exceptions.WrongPath;

/**
 * @author Tamir
 *
 */
public class FileEncryptor {
    private EncryptionAlgorithm EA;

    public FileEncryptor(EncryptionAlgorithm EA) {
        this.EA = EA;
    }

    public void encryptFile(String filePath) throws IOException, WrongPath {
        String data = FileOperations.getData(filePath);
        ArrayList<String> encryptedData = EA.encrypt(data);
        String newFilePath = FileOperations.storeNewData(encryptedData.get(1),
                filePath, FileOperations.Action.ENCRYPT);
        System.out.println(
                "The location of the encrypted file is: " + newFilePath);
        String keyPath = FileOperations.storeKey(filePath,
                encryptedData.get(0));
        System.out.println("The location of the key file is: " + keyPath);
    }

    public void decryptFile(String filePath, String keyPath)
            throws IOException, InvalidEncryptionKeyException, WrongPath {
        String data = FileOperations.getData(filePath);
        String key = FileOperations.getData(keyPath);
        String decryptedData = EA.decrypt(data, key);
        String newFilePath = FileOperations.storeNewData(decryptedData,
                filePath, FileOperations.Action.DECRYPT);
        System.out.println(
                "The location of the decrypted file is: " + newFilePath);
    }

}
