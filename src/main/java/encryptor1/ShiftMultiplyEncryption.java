/**
 * 
 */
package encryptor1;

/**
 * @author Tamir
 *
 */
public class ShiftMultiplyEncryption extends Encryption
        implements EncryptionAlgorithm {

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#generateKey()
     */
    @Override
    protected int generateKey() {
        int key = (int) ((Math.random() * 256) + 1);
        return key;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#addKey(java.lang.String, int)
     */
    @Override
    protected String addKey(String data, int key) {
        String newStr = "";
        int encrypt_key = powMod(5, key, 256);
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch * encrypt_key, 256));
        }
        return newStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#removeKey(java.lang.String, int)
     */
    @Override
    protected String removeKey(String data, int key) {
        String newStr = "";
        int modulu = 256;
        int encrypt_key = powMod(5, key, modulu);
        int decrypt_key = inverse(encrypt_key, modulu);
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch * decrypt_key, modulu));
        }
        return newStr;
    }

    /**
     * @param p
     * @param q
     * @return
     */
    private int[] gcd(int p, int q) {
        if (q == 0)
            return new int[] { p, 1, 0 };
        int[] vals = gcd(q, p % q);
        int d = vals[0];
        int a = vals[2];
        int b = vals[1] - (p / q) * vals[2];
        return new int[] { d, a, b };
    }

    /**
     * @param k
     * @param n
     * @return
     */
    private int inverse(int k, int n) {
        int[] vals = gcd(k, n);
        int d = vals[0];
        int a = vals[1];
        // int b = vals[2];
        if (d > 1) {
            System.out.println("Inverse does not exist.");
            return 0;
        }
        if (a > 0)
            return a;
        return n + a;
    }

    /**
     * @param a
     * @param b
     * @param n
     * @return
     */
    private int powMod(int a, int b, int n) {
        int pow = 1;
        for (int i = 0; i < b; i++) {
            pow = Math.floorMod(pow * a, n);
        }
        return pow;
    }
}
