package encryptor1;

public interface EventListener {
	public void encryptionStarted();

	public void encryptionEnded(EncryptionLogEventArgs args);

	public void decryptionStarted();

	public void decryptionEnded(EncryptionLogEventArgs args);

}
