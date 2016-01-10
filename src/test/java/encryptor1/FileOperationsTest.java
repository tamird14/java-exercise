package encryptor1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import encryptor1.Exceptions.WrongFilePath;

public class FileOperationsTest {

	@Test
	public void testGetData() throws WrongFilePath, IOException {
		PrintWriter writer;
		String fileName = "temporary file.txt";
		String text = "This is the text";
		writer = new PrintWriter("temporary file.txt", "UTF-8");
		writer.print(text);
		writer.close();
		String data = FileOperations.getData("temporary file.txt");
		assertEquals(data, text);
		File file = new File(fileName);
		file.delete();
	}

	@Test(expected = Exceptions.WrongFilePath.class)
	public void testGetDataWrongPath() throws WrongFilePath {
		FileOperations.getData("wrong path.txt");
	}

	@Test
	public void testStoreKey() throws IOException {
		String key = "1";
		String fileName = "temp file.txt";
		String newFileExpectedName = "key.txt";
		FileOperations.storeKey(fileName, key);
		File file = new File(newFileExpectedName);
		assertTrue(file.exists() && !file.isDirectory());
		FileReader fileReader;
		fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		int ch;
		String data = "";
		while ((ch = reader.read()) != -1) {
			data += (char) ch;
		}
		fileReader.close();
		assertEquals(key, data);

		file.delete();
	}

	@Test
	public void testWriteToFile() throws IOException {
		String data = "This is text";
		String fileName = "temp file.txt";
		FileOperations.writeToFile(fileName, data);
		File file = new File(fileName);
		assertTrue(file.exists() && !file.isDirectory());
		String actual = new String(Files.readAllBytes(Paths.get(fileName)));
		assertEquals(data, actual);
		file.delete();
	}

	@Test
	public void testGetEncryptedFileNameLocalPath() {
		String fileName = "temp file.txt";
		String expected = "temp file_encrypted.txt";
		String actual = FileOperations.getEncryptedFileName(fileName);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncryptedFileNameFullPath() {
		String fileName = "c:\\dir1\\dir2\\name.txt";
		String expected = "c:\\dir1\\dir2\\name_encrypted.txt";
		String actual = FileOperations.getEncryptedFileName(fileName);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncryptedFileNamePathWithDot() {
		String fileName = "c:\\dir1\\dir.2\\file.name.txt";
		String expected = "c:\\dir1\\dir.2\\file.name_encrypted.txt";
		String actual = FileOperations.getEncryptedFileName(fileName);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetDecryptedFileNameLocalPath() {
		String fileName = "temp file.txt";
		String expected = "temp file_decrypted.txt";
		String actual = FileOperations.getDecryptedFileName(fileName);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetDecryptedFileNameFullPath() {
		String fileName = "c:\\dir1\\dir2\\name.txt";
		String expected = "c:\\dir1\\dir2\\name_decrypted.txt";
		String actual = FileOperations.getDecryptedFileName(fileName);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetDecryptedFileNamePathWithDot() {
		String fileName = "c:\\dir1\\dir.2\\file.name.txt";
		String expected = "c:\\dir1\\dir.2\\file.name_decrypted.txt";
		String actual = FileOperations.getDecryptedFileName(fileName);
		assertEquals(expected, actual);
	}

}
