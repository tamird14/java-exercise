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

import encryptor1.Exceptions.WrongPath;

/**
 * @author Tamir
 *
 */
public class FileOperations {
    public enum Action {
        DECRYPT, ENCRYPT
    }

    /**
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getData(String filePath) throws WrongPath {
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
            throw new WrongPath();
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
        return writeToFile(newPath, key);
    }

    /**
     * @param data
     * @param originalPath
     * @param action
     * @return
     * @throws IOException
     */
    public static String storeNewData(String data, String originalPath,
            Action action) throws IOException {
        String fileName = originalPath.substring(0,
                originalPath.lastIndexOf('.'));
        String fileExtension = originalPath
                .substring(originalPath.lastIndexOf('.'));
        String newPath = fileName;
        if (action == Action.DECRYPT) {
            newPath += "_decrypted" + fileExtension;
        } else {
            newPath += "_encrypted" + fileExtension;
        }
        return writeToFile(newPath, data);
    }

    /**
     * @param path
     * @param data
     * @return
     * @throws IOException
     */
    private static String writeToFile(String path, String data)
            throws IOException {
        File newFile = new File(path);
        newFile.createNewFile();
        BufferedWriter output = new BufferedWriter(new FileWriter(newFile));
        output.write(data);
        output.close();
        return newFile.getPath();
    }
}
