package nz.co.dav.guava.sample.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFileExample {
	public static String ORIGINAL_FILE = "/sampleTextFileOne.txt";
	public static String COPY_FILE = "/tmp/copy.txt";

	public static void main(String[] args) {
		File original = new File(CopyFileExample.class.getResource(ORIGINAL_FILE).getFile());
		File copy = new File(COPY_FILE);
		byte[] buffer = new byte[1024];
		int numberRead;
		try (FileInputStream is = new FileInputStream(original); FileOutputStream os = new FileOutputStream(copy, false)) {
			while ((numberRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, numberRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
