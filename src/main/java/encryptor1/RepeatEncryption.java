package encryptor1;

import java.io.IOException;
import java.util.ArrayList;

import encryptor1.Exceptions.InvalidEncryptionKeyException;

public class RepeatEncryption implements EncryptionAlgorithm {
    int numberOfRepetitions;
    EncryptionAlgorithm EA;

    public RepeatEncryption(int numberOfRepetitions, EncryptionAlgorithm EA) {
        this.numberOfRepetitions = numberOfRepetitions;
        this.EA = EA;
    }

    public ArrayList<String> encrypt(String data) throws IOException {
        ArrayList<String> dataKey = this.EA.encrypt(data);
        String encryptedData = dataKey.get(1);
        int key = Integer.parseInt(dataKey.get(0));
        for (int i = 0; i < numberOfRepetitions - 1; i++) {
            encryptedData = this.EA.encryptWithKey(encryptedData, key);
        }
        dataKey.set(1, encryptedData);
        return dataKey;
    }

    public String decrypt(String data, String keyString)
            throws InvalidEncryptionKeyException {
        String encryptedData = data;
        for (int i = 0; i < numberOfRepetitions; i++) {
            encryptedData = this.EA.decrypt(encryptedData, keyString);
        }
        return encryptedData;
    }

    public String encryptWithKey(String data, int key) throws IOException {
        String encryptedData = data;
        for (int i = 0; i < numberOfRepetitions; i++) {
            encryptedData = this.EA.encryptWithKey(encryptedData, key);
        }
        return encryptedData;
    }

    public int getKeyStrengh() {
        return this.EA.getKeyStrengh();
    }

}
