package encryptor1;

public class XorAction extends Action {

	public XorAction(String data) {
		super(data);
	}

	public XorAction(String data, int key) {
		super(data, key);
	}

	@Override
	public String performAction() {
		String newStr = "";
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch ^ key, 128));
        }
        return newStr;
	}

}
