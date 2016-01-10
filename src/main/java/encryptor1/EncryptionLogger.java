package encryptor1;

import java.util.Observable;
import java.util.Observer;

public class EncryptionLogger implements EventListener, Observer {
	private long startTime;
	private long endTime;

	public EncryptionLogger() {
		// TODO Auto-generated method stub
	}

	public void encryptionStarted() {
		this.startTime = System.currentTimeMillis();
	}

	public void encryptionEnded(EncryptionLogEventArgs args) {
		this.endTime = System.currentTimeMillis();
		System.out.println("The encryption for file " + args.getOriginalFile()
				+ " with algorithm " + args.getAlgorithm() + " took "
				+ (endTime - startTime)
				+ " miliseconds. The encrypted file is located in file "
				+ args.getNewFile());
	}

	public void decryptionStarted() {
		this.startTime = System.currentTimeMillis();
	}

	public void decryptionEnded(EncryptionLogEventArgs args) {
		this.endTime = System.currentTimeMillis();
		System.out.println("The decryption for file " + args.getOriginalFile()
				+ " with algorithm " + args.getAlgorithm() + " took "
				+ (endTime - startTime)
				+ " miliseconds. The decrypted file is located in file "
				+ args.getNewFile());
	}

	public void update(Observable arg0, Object arg1) {
		EncryptionLogEventArgs args = (EncryptionLogEventArgs) arg1;
		if (args.getEvent().equals("encryption started"))
			encryptionStarted();
		else if (args.getEvent().equals("encryption ended"))
			encryptionEnded(args);
		else if (args.getEvent().equals("decryption started"))
			decryptionStarted();
		else if (args.getEvent().equals("decryption ended"))
			decryptionEnded(args);
	}

}
