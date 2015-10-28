package encryptor1;

public class XorEncryption extends Encryption {

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#addKey(java.lang.String, int)
     */
    @Override
    protected String addKey(String data, int key) {
        String newStr = "";
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch ^ key, 256));
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
        return addKey(data, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see encryptor1.Encryption#generateKey()
     */
    @Override
    protected int generateKey() {
        return ((int) ((Math.random() * Integer.MAX_VALUE) + 1));
    }
}
