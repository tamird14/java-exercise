package encryptor1;

public abstract class DoubleKey<T> {
	private T key1;
	private T key2;

	public DoubleKey() {
		key1 = generateKey();
		key2 = generateKey();
	}
	
	public DoubleKey(T key1, T key2){
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public T getKey1(){
		return key1;
	}
	
	public T getKey2(){
		return key2;
	}

	public abstract T generateKey();

}
