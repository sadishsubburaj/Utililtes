package com.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileOperations {
	private String destinationPath = null;
	File f = null;
	public HashMap<String, FileWriter> fileObjStore = new HashMap<String, FileWriter>();
	FileWriter fw = null;

	public FileOperations(String destinationPath) {
		this.destinationPath = destinationPath;
	}

	public boolean checkFileExists(String fileName) {
		fileName = fileNamecheckAndReplace(fileName);
		return fileObjStore.containsKey(fileName);
	}

	public void createFile(String fileName) throws IOException {
        fileName = fileNamecheckAndReplace(fileName);
		File f = new File(destinationPath + "//" + fileName);
		f.createNewFile();
		FileWriter fw = new FileWriter(destinationPath + "//" + fileName,true);
		fileObjStore.put(fileName, fw);
	}

	public boolean addLogToFile(String log, String fileName) throws IOException {
        fileName = fileNamecheckAndReplace(fileName);
        FileWriter fw2 = fileObjStore.get(fileName);
		fw2.append(log+"\n");
		return true;
	}
	
	private String fileNamecheckAndReplace(String fileName) {
		if(fileName.contains("/")) {
			fileName = fileName.replace('/', '-');
		}
		return fileName;
	}
}
