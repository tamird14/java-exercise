package encryptor1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import encryptor1.Exceptions.*;

public class SyncDirectoryProcessor<T> extends Observable {
	private IEncryptionAlgorithm<T> EA;
	private T key;

	public SyncDirectoryProcessor(IEncryptionAlgorithm<T> EA, T key) {
		this.EA = EA;
		this.key = key;
	}

	public void encryptDirectory(String dirPath)
			throws WrongDirectoryPath, WrongFilePath, IOException {
		validateDirectory(dirPath);
		List<File> files = getTxtFiles(dirPath);
		File encryptedDirectory = new File(dirPath + "\\encrypted");
		if (!encryptedDirectory.mkdir()) {
			// TODO:
		}
		EncryptionLogEventArgs args = new EncryptionLogEventArgs("encryption started", dirPath, encryptedDirectory.getAbsolutePath(), EA.toString())
		encryptFiles(files, encryptedDirectory.getAbsolutePath());

	}

	private void encryptFiles(List<File> files, String encryptedDirectory)
			throws WrongFilePath, IOException {
		for (File file : files) {
			String filePath = file.getAbsolutePath();
			String fileName = file.getName();
			FileEncryptor<T> FE = new FileEncryptor<T>(EA, key);
			FE.encryptFile(filePath, encryptedDirectory + "\\" + fileName,
					false);
		}
	}

	private List<File> getTxtFiles(String dirPath) {
		File folder = new File(dirPath);
		ArrayList<File> txtFiles = new ArrayList<File>();
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getAbsolutePath().endsWith(".txt"))
				txtFiles.add(file);
		}
		return txtFiles;
	}

	private void validateDirectory(String dirPath) throws WrongDirectoryPath {
		File file = new File(dirPath);
		if (!file.isDirectory()) {
			throw new WrongDirectoryPath(dirPath);
		}
	}

}
