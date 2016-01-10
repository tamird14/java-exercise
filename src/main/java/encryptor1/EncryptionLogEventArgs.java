package encryptor1;

public class EncryptionLogEventArgs {
	private String event;
	private String originalFile;
	private String newFile;
	private String algorithm;

	public EncryptionLogEventArgs(String event, String originalFile,
			String newFile, String algorithm) {
		this.event = event;
		this.originalFile = originalFile;
		this.newFile = newFile;
		this.algorithm = algorithm;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getOriginalFile() {
		return originalFile;
	}

	public String getNewFile() {
		return newFile;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((algorithm == null) ? 0 : algorithm.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((newFile == null) ? 0 : newFile.hashCode());
		result = prime * result
				+ ((originalFile == null) ? 0 : originalFile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncryptionLogEventArgs other = (EncryptionLogEventArgs) obj;
		if (algorithm == null) {
			if (other.algorithm != null)
				return false;
		} else if (!algorithm.equals(other.algorithm))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (newFile == null) {
			if (other.newFile != null)
				return false;
		} else if (!newFile.equals(other.newFile))
			return false;
		if (originalFile == null) {
			if (other.originalFile != null)
				return false;
		} else if (!originalFile.equals(other.originalFile))
			return false;
		return true;
	}
}
