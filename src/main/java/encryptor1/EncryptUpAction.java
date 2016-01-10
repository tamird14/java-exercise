package encryptor1;

public class EncryptUpAction extends Action {

	public EncryptUpAction(String data) {
		super(data);
	}

	public EncryptUpAction(String data, int key) {
		super(data, key);
	}

	@Override
	public String performAction() {
		String newStr = "";
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch + key, 128));
        }
        return newStr;
	}

}
