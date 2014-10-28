package nz.co.dav.guava.sample.file;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class GuavaCopyFileExample {
	public static String ORIGINAL_FILE = "/sampleTextFileOne.txt";
	public static String COPY_FILE = "/tmp/copy.txt";

	public static void main(String[] args) {
		File original = new File(CopyFileExample.class.getResource(ORIGINAL_FILE).getFile());
		File copy = new File(COPY_FILE);
		try {
			Files.copy(original, copy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
