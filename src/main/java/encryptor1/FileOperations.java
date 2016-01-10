/**
 * 
 */
package encryptor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import encryptor1.Exceptions.WrongFilePath;

/**
 * @author Tamir
 *
 */
public class FileOperations {

	/**
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getData(String filePath) throws WrongFilePath {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(filePath));
			String content = "";
			int ch;
			while ((ch = reader.read()) != -1) {
				content += (char) ch;
			}
			reader.close();
			return content;
		} catch (IOException e) {
			throw new WrongFilePath(filePath);
		}
	}

	/**
	 * @param originalPath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String storeKey(String originalPath, String key)
			throws IOException {
		String newPath = originalPath.substring(0,
				originalPath.lastIndexOf('\\') + 1) + "key.txt";
		writeToFile(newPath, key);
		return newPath;

	}

	public static String getEncryptedFileName(String originalPath) {
		String fileName = originalPath.substring(0,
				originalPath.lastIndexOf('.'));
		String fileExtension = originalPath
				.substring(originalPath.lastIndexOf('.'));
		String newPath = fileName + "_encrypted" + fileExtension;
		return newPath;
	}

	public static String getDecryptedFileName(String originalPath) {
		String fileName = originalPath.substring(0,
				originalPath.lastIndexOf('.'));
		String fileExtension = originalPath
				.substring(originalPath.lastIndexOf('.'));
		String newPath = fileName + "_decrypted" + fileExtension;
		return newPath;
	}

	public static void writeToFile(String path, String data)
			throws IOException {
		File newFile = new File(path);
		newFile.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(newFile));
		output.write(data);
		output.close();
	}
}
