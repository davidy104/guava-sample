package nz.co.dav.guava.sample.file;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class GuavaMoveFileExample {
	public static String ORIGINAL_FILE = "/tmp/copy.txt";
	public static String NEW_FILE = "/tmp/newFile.txt";

	public static void main(String[] args) {
		File original = new File(ORIGINAL_FILE);
		File newFile = new File(NEW_FILE);
		try {
			Files.move(original, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
