package encryptor1;

import java.util.Comparator;

public class KeyComparator implements Comparator<EncryptionAlgorithm> {

    public int compare(EncryptionAlgorithm o1, EncryptionAlgorithm o2) {
        return o1.getKeyStrengh() - o2.getKeyStrengh();
    }

}