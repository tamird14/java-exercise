package encryptor1;

public class DoubleKeyInteger extends DoubleKey<Integer> {

	public DoubleKeyInteger() {
		super();
	}

	public DoubleKeyInteger(Integer key1, Integer key2) {
		super(key1, key2);
	}

	@Override
	public Integer generateKey() {
		return ((int) ((Math.random() * 128) + 1));
	}

}
