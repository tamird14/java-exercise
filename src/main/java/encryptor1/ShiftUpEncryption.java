/**
 * 
 */
package encryptor1;

/**
 * @author Tamir
 *
 */
public class ShiftUpEncryption extends Encryption {

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#generateKey()
     */
    protected int generateKey() {
        return ((int) ((Math.random() * Integer.MAX_VALUE) + 1));
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#addKey(java.lang.String, int)
     */
    protected String addKey(String data, int key) {
        String newStr = "";
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch + key, 128));
        }
        return newStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#removeKey(java.lang.String, int)
     */
    protected String removeKey(String data, int key) {
        return addKey(data, -key);
    }

}
