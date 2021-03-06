/**
 * 
 */
package encryptor1;

import java.io.IOException;
import java.util.Scanner;

import encryptor1.Exceptions.InvalidEncryptionKeyException;
import encryptor1.Exceptions.WrongFilePath;

/**
 * @author Tamir
 *
 */
public class Main {
	public static void main(String[] args)
			throws IOException, InvalidEncryptionKeyException, WrongFilePath {
		DoubleKeyInteger keys = new DoubleKeyInteger();
		IEncryptionAlgorithm<DoubleKey<Integer>> EA = new DoubleEncryption<Integer>(
				new ShiftUpEncryption());
		FileEncryptor<DoubleKey<Integer>> fileEncryptor = new FileEncryptor<DoubleKey<Integer>>(
				EA, keys);
		Scanner in = new Scanner(System.in);
		String action = getAction(in);
		String path = "";
		System.out.println("Please enter the Path to the source file: ");
		path = in.nextLine();
		if (action.equals("e")) {
			String newFilePath = FileOperations.getEncryptedFileName(path);
			fileEncryptor.encryptFile(path, newFilePath, true);
		} else {
			System.out.println("Please enter the Path to the key file: ");
			String keyPath = in.nextLine();
			fileEncryptor.decryptFile(path, keyPath);
		}
		in.close();
	}

	private static String getAction(Scanner in) {
		System.out.println("Would you like to encrypt or decrypt?");
		System.out.print("To encrypt enter 'e', to decrypt enter 'd': ");
		String action = in.nextLine();
		while (!(action.equals("e") || action.equals("d"))) {
			System.out.print("Wrong input! Please enter again: ");
			action = in.nextLine();
		}
		return action;
	}
}
