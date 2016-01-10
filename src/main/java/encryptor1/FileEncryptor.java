/**
 * 
 */
package encryptor1;

import java.io.IOException;

import encryptor1.Exceptions.*;
import java.util.Observable;

/**
 * @author Tamir
 *
 */
public class FileEncryptor<T> extends Observable {
	private IEncryptionAlgorithm<T> EA;
	private T key;

	public FileEncryptor(IEncryptionAlgorithm<T> EA, T key) {
		this.EA = EA;
		this.key = key;
	}

	public void encryptFile(String filePath, String newFilePath, boolean storeKey) throws IOException, WrongFilePath {
		String data = FileOperations.getData(filePath);
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(
				"encryption started", filePath, newFilePath,
				this.EA.toString());
		setChanged();
		notifyObservers(args);
		String encryptedData = EA.encrypt(data, key);
		args.setEvent("encryption ended");
		setChanged();
		notifyObservers(args);
		endEncryption(filePath, newFilePath, encryptedData, storeKey);
	}

	public void endEncryption(String filePath, String newFilePath,
			String encryptedData, boolean storeKey) throws IOException {
		FileOperations.writeToFile(newFilePath, encryptedData);
		if (storeKey){
			String keyPath = FileOperations.storeKey(filePath, key.toString());
			System.out.println("The location of the key file is: " + keyPath);
		}
	}

	public void decryptFile(String filePath, String keyPath)
			throws IOException, InvalidEncryptionKeyException, WrongFilePath {
		String data = FileOperations.getData(filePath);
		String key = FileOperations.getData(keyPath);
		String newFilePath = FileOperations.getDecryptedFileName(filePath);
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(
				"decryption started", filePath, newFilePath,
				this.EA.toString());
		setChanged();
		notifyObservers(args);
		String decryptedData = EA.decrypt(data, key);
		args.setEvent("decryption_ended");
		setChanged();
		notifyObservers(args);
		FileOperations.writeToFile(newFilePath, decryptedData);
		System.out.println(
				"The location of the decrypted file is: " + newFilePath);
	}

}
