package encryptor1;

public abstract class Action {
	protected int key;
	protected String data;
	protected String changedData;

	public Action(String data) {
		key = generateKey();
		this.data = data;
	}

	public Action(String data, int key) {
		this.key = key;
		this.data = data;
	}

	private int generateKey() {
		return ((int) ((Math.random() * 128) + 1));
	}

	public int getKey() {
		return key;
	}

	public void changeData(String data) {
		this.data = data;
	}

	public String getChangedData() {
		return changedData;
	}

	public abstract String performAction();
}
