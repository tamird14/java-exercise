package encryptor1;

public class EncryptMultiplyAction extends Action {

	public EncryptMultiplyAction(String data) {
		super(data);
	}
	
	public EncryptMultiplyAction(String data, int key) {
		super(data, key);
	}

	@Override
	public String performAction() {
		String newStr = "";
        int encrypt_key = powMod(5, key, 128);
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch * encrypt_key, 128));
        }
        return newStr;
	}
	
	private int powMod(int a, int b, int n) {
        int pow = 1;
        for (int i = 0; i < b; i++) {
            pow = Math.floorMod(pow * a, n);
        }
        return pow;
    }

}
