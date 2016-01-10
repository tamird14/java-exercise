package encryptor1;

public class DecryptUpAction extends Action {
	
	public DecryptUpAction(String data) {
		super(data);
	}
	
	public DecryptUpAction(String data, int key){
		super(data, key);
	}

	@Override
	public String performAction() {
		String newStr = "";
        for (char ch : data.toCharArray()) {
            newStr += (char) (Math.floorMod(ch - key, 128));
        }
        return newStr;
	}

}
