package encryptor1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import encryptor1.Exceptions.WrongPath;
import encryptor1.FileOperations.Action;

public class FileOperationsTest {

    @Test
    public void testGetData() throws WrongPath, IOException {
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
    
    @Test(expected = Exceptions.WrongPath.class)
    public void testGetDataWrongPath() throws WrongPath{
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
    public void testStoreNewDataEncrypt() throws IOException {
        String data = "This is text";
        String fileName = "temp file.txt";
        String newFileExpectedName = "temp file_encrypted.txt";
        FileOperations.storeNewData(data, fileName, Action.ENCRYPT);
        File file = new File(newFileExpectedName);
        assertTrue(file.exists() && !file.isDirectory());
        file.delete();
    }

    @Test
    public void testStoreNewDataDecrypt() throws IOException {
        String data = "This is text";
        String fileName = "temp file.txt";
        String newFileExpectedName = "temp file_decrypted.txt";
        FileOperations.storeNewData(data, fileName, Action.DECRYPT);
        File file = new File(newFileExpectedName);
        assertTrue(file.exists() && !file.isDirectory());
        file.delete();
    }
}
