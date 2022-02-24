package com.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SplitFile {
	private static String log = null;
	private static final String regexp = ".*ProductName=\"([^\"]+)\" .*";
	private static String match = null;
	public static HashMap <String, FileWriter>iterable;

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("D:\\ArcSight\\JIRA\\8.4.0\\checkpoint\\CPR81-Logs-SyslogFormat\\15.214.198.185.log");
		Scanner sc = new Scanner(System.in);
		Long l = System.currentTimeMillis();
		System.out.println("Enter the destination path");
		String DestinationPath = sc.nextLine();
		BufferedReader bfr = new BufferedReader(fr);
		RegexOperations ro = new RegexOperations();
		FileOperations fo = new FileOperations(DestinationPath);
		while (bfr.ready()) {
			log = bfr.readLine();
			match = ro.regexops(log, regexp)+".log";
			if (!fo.checkFileExists(match)) {
				fo.createFile(match);
			}
			fo.addLogToFile(log, match);
		}
		System.out.println("success");
		System.out.println("The time taken is:" + (System.currentTimeMillis()-l)/1000+"s");
		bfr.close();
		fr.close();
		HashMap hm = fo.fileObjStore;
		Iterator i = hm.entrySet().iterator();
		while(i.hasNext()) {
			 Map.Entry<String, FileWriter> entry = (Entry<String, FileWriter>) i.next();
			 (entry.getValue()).close();
		}
	}
}
